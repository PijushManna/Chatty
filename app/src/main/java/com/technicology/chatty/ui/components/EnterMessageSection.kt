package com.technicology.chatty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technicology.chatty.ui.theme.ChattyTheme

@Composable
fun EnterMessageSection(onMessageAdded: (String) -> Unit) {
    var message by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        OutlinedTextField(
            value = message, onValueChange = { message = it }, placeholder = {
            Text("Enter your message here")
        }, shape = RoundedCornerShape(24.dp), modifier = Modifier.padding(16.dp)
        )
        Button(onClick = {
            onMessageAdded(message)
        }, modifier = Modifier.padding(8.dp)) {
            Icon(Icons.AutoMirrored.Default.Send, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewEnterMessageSection() {
    ChattyTheme {
        EnterMessageSection{

        }
    }
}