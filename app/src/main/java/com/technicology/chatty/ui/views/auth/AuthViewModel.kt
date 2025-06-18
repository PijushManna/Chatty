package com.technicology.chatty.ui.views.auth

import androidx.lifecycle.ViewModel
import com.technicology.chatty.repo.manager.FirebaseAuthManager
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel : ViewModel() {
    var auth = FirebaseAuthManager
    var message = MutableStateFlow<String>("")

    fun signUp(email: String, password: String): Boolean{
        return auth.signUp(email,password)
    }

    fun login(email: String, password: String): Boolean {
       return auth.login(email, password)
    }

    fun logout() = auth.logout()

    fun isUserLoggedIn() = auth.isUserLoggedIn()
}