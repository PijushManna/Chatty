package com.technicology.chatty.ui.views.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicology.chatty.repo.auth.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepo: AuthRepo) : ViewModel() {
    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.None)
    val authUiState: StateFlow<AuthUiState> = _authUiState

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authRepo.signUp(email, password) { isSuccessful, e ->

                _authUiState.value =
                    if (isSuccessful) AuthUiState.Successful else AuthUiState.Failed(
                        e?.localizedMessage ?: "Something went wrong"
                    )
            }
        }
    }

    fun updateUser(nickname: String, about: String, avatar:Int){
        viewModelScope.launch {
            authRepo.apply {
                updateNickname(nickname)
                updateAbout(about)
                updateAvatar(avatar)
                updateCurrentUserInLocal()
            }
        }
    }

    fun isUserSignedIn() = authRepo.isUserSignedIn()

    fun googleSignUp(){

    }

    fun login(email: String, password: String) {
//        return auth.login(email, password)
    }

    fun signOut(){
        viewModelScope.launch {
            authRepo.signOut()
        }
    }

}