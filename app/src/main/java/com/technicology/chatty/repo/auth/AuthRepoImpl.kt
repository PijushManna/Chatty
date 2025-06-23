package com.technicology.chatty.repo.auth

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.ClearCredentialException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.technicology.chatty.repo.local.dao.UserDao
import com.technicology.chatty.repo.local.entity.User
import com.technicology.chatty.repo.model.UserStatus
import com.technicology.chatty.utils.Keys
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking

class AuthRepoImpl @Inject constructor(private val userDao: UserDao) : AuthRepo {
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var credentialManager: CredentialManager
    private lateinit var currentUser: User

    override suspend fun signUp(
        email: String, password: String, isSuccessful: (Boolean, Exception?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
            runBlocking {
                if (it.isSuccessful && it.result.user != null) {
                    val data = it.result.user!!
                    addNewUser(
                        User(
                            id = data.uid,
                            name = data.displayName.orEmpty(),
                            email = data.email.orEmpty(),
                            phone = data.phoneNumber.orEmpty(),
                            about = "",
                            image = data.photoUrl.toString(),
                            avatar = -1,
                            status = UserStatus.ACTIVE.name,
                            isCurrentUser = true
                        )
                    )
                }
            }

            isSuccessful(it.isSuccessful, it.exception)
        }
    }

    override suspend fun signIn(
        email: String, password: String, isSuccessful: (Boolean, Exception?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            isSuccessful(it.isSuccessful, it.exception)
        }
    }

    override suspend fun updateNickname(nickname: String) {
        currentUser.name = nickname
    }

    override suspend fun updateAbout(aboutMsg: String) {
        currentUser.about = aboutMsg
    }

    override suspend fun updateAvatar(avatar: Int) {
        currentUser.image = avatar.toString()
    }

    override suspend fun updateCurrentUserInLocal() {
        userDao.update(currentUser)
    }

    override suspend fun signOut() {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.signOut()
            try {
                if (::credentialManager.isInitialized) {
                    val clearRequest = ClearCredentialStateRequest()
                    credentialManager.clearCredentialState(clearRequest)
                }
                userDao.deleteAllUsers()
            } catch (e: ClearCredentialException) {
                Log.e("FirebaseAuth", "Couldn't clear user credentials: ${e.localizedMessage}")
            }
        }
    }

    override suspend fun signUpUsingGoogle(context: Context, isSuccessful: (Boolean) -> Unit) {
        credentialManager = CredentialManager.create(context)
        val googleIdOption = getGoogleIdOption()
        val request = createGoogleSignInRequest(googleIdOption)
        val credentialResponse = credentialManager.getCredential(context, request)
        handleSignIn(credentialResponse.credential, isSuccessful)
    }

    private fun getGoogleIdOption(): GetGoogleIdOption {
        return GetGoogleIdOption.Builder().setServerClientId(Keys.GOOGLE_SIGN_UP_KEY)
            .setFilterByAuthorizedAccounts(true).build()
    }

    private fun createGoogleSignInRequest(googleIdOption: GetGoogleIdOption): GetCredentialRequest {
        return GetCredentialRequest.Builder().addCredentialOption(googleIdOption).build()
    }

    private fun handleSignIn(credential: Credential, isSuccessful: (Boolean) -> Unit) {
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken, isSuccessful)
        } else {
            Log.w("FirebaseAuth", "Credential is not of type Google ID!")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String, isSuccessful: (Boolean) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(OnCompleteListener {
            isSuccessful(it.isSuccessful)
        })
    }

    override suspend fun addNewUser(user: User) {
        userDao.insert(user)
        currentUser = user
    }

    override fun isUserSignedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun getCurrentUser(): User {
        if (!::currentUser.isInitialized) {
            currentUser = userDao.getCurrentUser()
        }
        return currentUser
    }
}