package com.technicology.chatty.ui.views.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.technicology.chatty.R
import com.technicology.chatty.repo.validations.Validator
import com.technicology.chatty.ui.theme.ChattyTheme
import com.technicology.chatty.ui.theme.PillShape
import com.technicology.chatty.utils.showToast

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    navigateToNextScreen: () -> Unit
) {
    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isButtonEnabled by rememberSaveable { mutableStateOf(false) }
    val uiState = authViewModel.authUiState.collectAsStateWithLifecycle()

    val typography = MaterialTheme.typography
    val textStyle = typography.bodyMedium.copy(fontSize = 24.sp)
    val spacing = dimensionResource(id = R.dimen.spacing_large)
    val spacingNormal = dimensionResource(id = R.dimen.spacing_normal)
    val textPadding = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_small))

    LaunchedEffect(email, password) {
        if (Validator.isEmailValid(email) && Validator.isPasswordValid(password)) {
            isButtonEnabled = true
        }
    }

    LaunchedEffect(uiState.value) {
        isButtonEnabled = when (uiState.value) {
            is AuthUiState.Failed -> {
                context.showToast(((uiState.value) as AuthUiState.Failed).error)
                true
            }
            AuthUiState.Successful-> {
                navigateToNextScreen()
                false
            }

            else -> {
                false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacing),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xlarge)))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.app_name),
                style = typography.headlineLarge.copy(fontSize = 60.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xxlarge)))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.hint_email), modifier = textPadding) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = PillShape,
            )

            Spacer(modifier = Modifier.height(spacingNormal))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.hint_password), modifier = textPadding) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                shape = PillShape,
            )

            Spacer(modifier = Modifier.height(spacingNormal))

            Text(
                text = stringResource(R.string.password_rule),
                style = typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            )
        }

        Column {
            Button(
                onClick = { authViewModel.signUp(email, password) },
                shape = PillShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                enabled = isButtonEnabled
            ) {
                Text(
                    text = stringResource(R.string.continue_btn),
                    modifier = textPadding,
                    style = textStyle.copy(fontWeight = FontWeight.ExtraBold)
                )
            }

            Spacer(modifier = Modifier.height(spacingNormal))

            Text(
                text = stringResource(R.string.or),
                style = typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(spacingNormal))

            OutlinedButton(
                onClick = {
                    authViewModel.googleSignUp()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = PillShape,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(R.string.google_signup), style = textStyle)
            }

            Spacer(modifier = Modifier.height(spacing))

            Text(
                text = buildAnnotatedString {
                append(stringResource(R.string.terms_prefix))
                pushStringAnnotation(tag = "TERMS", annotation = "terms")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(R.string.terms_link))
                }
                pop()
            }, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }, style = typography.bodySmall, textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUpScreen() {
    ChattyTheme {
//        SignUpScreen({ _, _ -> }) { }
    }
}