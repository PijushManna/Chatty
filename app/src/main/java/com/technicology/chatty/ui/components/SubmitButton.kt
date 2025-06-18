package com.technicology.chatty.ui.components

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SubmitButton(modifier: Modifier, text: String, isEnabled: Boolean, onClick:() -> Unit) {
    ElevatedButton(onClick = onClick, modifier = modifier, enabled = isEnabled) {
        Text(text = text)
    }
}