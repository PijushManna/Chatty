package com.technicology.chatty.repo.auth

import android.content.Context
import com.technicology.chatty.repo.local.entity.User

interface AuthRepo {
    suspend fun signUp(email: String, password: String, isSuccessful: (Boolean, Exception?) -> Unit)
    suspend fun signIn(email: String, password: String,  isSuccessful: (Boolean, Exception?) -> Unit)
    suspend fun signOut()
    suspend fun signUpUsingGoogle( context:Context, isSuccessful: (Boolean) -> Unit)
    fun isUserSignedIn(): Boolean
    suspend fun updateNickname(nickname: String)
    suspend fun updateAbout(nickname: String)
    suspend fun updateAvatar(avatar: Int)
    suspend fun updateCurrentUserInLocal()
    suspend fun getCurrentUser():User
    suspend fun addNewUser(user: User)
}