package com.technicology.chatty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technicology.chatty.ui.theme.ChattyTheme
import com.technicology.chatty.ui.utils.Constants

@Composable
fun ChatBubble(message: String) {
    Surface(shape = Constants.BubbleShape, modifier = Modifier) {
        Column {
            Text(message, Modifier.background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp))
        }
    }
}

@Composable
fun ReversedChatBubble(message: String) {
    Surface(shape = Constants.ReversedBubbleShape, modifier = Modifier) {
        Column {
            Text(message, Modifier.background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ViewChatBubble() {
    ChattyTheme {
        ChatBubble("This is a sample message")
    }
}