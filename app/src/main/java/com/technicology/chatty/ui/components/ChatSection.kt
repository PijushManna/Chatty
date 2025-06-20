package com.technicology.chatty.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technicology.chatty.R
import com.technicology.chatty.ui.theme.ChattyTheme
import com.technicology.chatty.ui.views.chat.ChatScreen

@Composable
fun ChatSection(userName: String, timestamp: String) {
    Column{
        Row(Modifier.fillMaxWidth().padding(8.dp)) {
            ProfilePicture(Modifier, image = R.drawable.ic_launcher_background)
            Spacer(Modifier.width(8.dp))
            Column {
                UsernameWithTimestamp(name = userName, timestamp = timestamp)
                LazyColumn{
                    items(3) {
                        ChatBubble("Sample message")
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ReversedChatSection(userName: String, timestamp: String) {
    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()){
        Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.End) {
            Spacer(Modifier.height(16.dp))
            Column {
                UsernameWithTimestamp(name = userName, timestamp = timestamp)
                LazyColumn{
                    items(3) {
                        ReversedChatBubble("Sample message")
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
            ProfilePicture(Modifier, image = R.drawable.ic_launcher_background)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewChatScreen() {
    ChattyTheme {
        ReversedChatSection("username","8:00 PM")
    }
}