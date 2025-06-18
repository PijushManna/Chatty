package com.technicology.chatty.ui.components

import android.provider.ContactsContract.DisplayNameSources.EMAIL
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technicology.chatty.ui.enum.InputType
import com.technicology.chatty.ui.enum.InputType.PASSWORD

@Composable
fun SignUpOrLoginInputText(inputType: InputType, value: String, callback: (String) -> Unit) {
    val label = when (inputType) {
        InputType.EMAIL -> "Email"
        PASSWORD -> "Password"
    }
    OutlinedTextField(label = { Text(label) }, value = value, onValueChange = {
        callback(it)
    }, leadingIcon = {
        when (inputType) {
            InputType.EMAIL -> Icon(imageVector = Icons.Default.Email, contentDescription = "person")
            PASSWORD -> Icon(imageVector = Icons.Default.Lock, contentDescription = "password")
        }
    },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        visualTransformation = if ( inputType == PASSWORD) PasswordVisualTransformation() else VisualTransformation.None
    )

}

