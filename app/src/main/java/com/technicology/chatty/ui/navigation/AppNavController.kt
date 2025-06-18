package com.technicology.chatty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.technicology.chatty.ui.views.auth.LoginScreen
import com.technicology.chatty.ui.views.auth.SignUpScreen
import com.technicology.chatty.ui.views.auth.AuthViewModel

@Composable
fun AppNavController() {
    val authViewModel: AuthViewModel = viewModel<AuthViewModel>()
    val navController = rememberNavController()
    NavHost(navController,LOGIN_SCREEN){
        composable(LOGIN_SCREEN) {
            LoginScreen(authViewModel, navController)
        }

        composable(SIGNUP_SCREEN) {
            SignUpScreen(authViewModel, navController)
        }
    }
}

const val LOGIN_SCREEN = "login"
const val SIGNUP_SCREEN = "signup"