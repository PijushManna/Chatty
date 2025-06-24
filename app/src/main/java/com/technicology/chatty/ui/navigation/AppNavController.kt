package com.technicology.chatty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.technicology.chatty.ui.views.auth.AuthViewModel
import com.technicology.chatty.ui.views.auth.AvatarNicknameScreen
import com.technicology.chatty.ui.views.auth.LoginScreen
import com.technicology.chatty.ui.views.auth.SignUpScreen
import com.technicology.chatty.ui.views.chat.ChatScreen
import com.technicology.chatty.ui.views.chat.ChatViewModel
import com.technicology.chatty.ui.views.home.HomeScreen
import com.technicology.chatty.ui.views.home.HomeViewModel

@Composable
fun AppNavController() {
    val authViewModel: AuthViewModel = viewModel<AuthViewModel>()
    val homeViewModel: HomeViewModel = viewModel<HomeViewModel>()
    val chatViewModel: ChatViewModel = viewModel<ChatViewModel>()
    val navController = rememberNavController()
    var nextScreen = if(authViewModel.isUserSignedIn()) HOME_SCREEN else SIGNUP_SCREEN


    NavHost(navController, nextScreen) {
        composable(LOGIN_SCREEN) {
            LoginScreen(authViewModel, navController)
        }

        composable(SIGNUP_SCREEN) {
            SignUpScreen(authViewModel) {
                navController.navigate(AVATAR_NICKNAME_SCREEN)
            }
        }

        composable(AVATAR_NICKNAME_SCREEN) {
            AvatarNicknameScreen { nickname, about, avatar ->
                authViewModel.updateUser(nickname, about, avatar)
                navController.navigate(HOME_SCREEN)
            }
        }

        composable(HOME_SCREEN) {
            HomeScreen(viewModel = homeViewModel, navController)
        }

        composable("$CHAT_SCREEN/{chatId}") {
            val chatId = it.arguments?.getString("chatId")
            ChatScreen(chatViewModel = chatViewModel, navController, chatId)
        }
    }
}

const val LOGIN_SCREEN = "login"
const val SIGNUP_SCREEN = "signup"
const val HOME_SCREEN = "home"
const val CHAT_SCREEN = "chat"
const val AVATAR_NICKNAME_SCREEN = "avatar_nickname"