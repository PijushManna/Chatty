package com.technicology.chatty.ui.views.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.technicology.chatty.R
import com.technicology.chatty.repo.validations.Validator
import com.technicology.chatty.repo.validations.Validator.isLenValid
import com.technicology.chatty.ui.components.SignUpOrLoginInputText
import com.technicology.chatty.ui.components.SubmitButton
import com.technicology.chatty.ui.enum.InputType
import com.technicology.chatty.ui.navigation.HOME_SCREEN
import com.technicology.chatty.ui.navigation.SIGNUP_SCREEN
import com.technicology.chatty.ui.theme.ChattyTheme

@Composable
fun LoginScreen(viewModel: AuthViewModel, navController: NavController) {
    var email: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var isButtonEnabled by rememberSaveable { mutableStateOf(false) }
    val message by viewModel.message.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(email, password) {
        if (Validator.isEmailValid(email) && isLenValid(password)) {
            isButtonEnabled = true
        }
    }

    LaunchedEffect(message) {
        if (!message.isBlank()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    if (viewModel.isUserLoggedIn()) {
        navController.navigate(HOME_SCREEN)
    } else {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(16.dp)
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Login ".uppercase(),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
                style = MaterialTheme.typography.titleLarge
            )
            SignUpOrLoginInputText(inputType = InputType.EMAIL, email) {
                email = it
            }
            SignUpOrLoginInputText(inputType = InputType.PASSWORD, password) {
                password = it
            }

            SubmitButton(
                Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                "Login",
                isEnabled = isButtonEnabled
            ) {
                if (viewModel.login(email, password)) {
                    navController.navigate(HOME_SCREEN)
                }
            }
            Spacer(Modifier.weight(1F))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Don't have an account?")
                TextButton(onClick = {
                    navController.navigate(SIGNUP_SCREEN)
                }, Modifier.padding(0.dp)) {
                    Text("Create New")
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginScreen() {
    ChattyTheme {}
}