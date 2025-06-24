package com.technicology.chatty.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technicology.chatty.R
import com.technicology.chatty.repo.model.ConsumableChatModel
import com.technicology.chatty.ui.theme.ChattyTheme

@Composable
fun ChatSection(model: ConsumableChatModel) {
    Column {
        Row(Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            ProfilePicture(Modifier, image = model.sender?.getAvatarImage() ?: 0)
            Spacer(Modifier.width(8.dp))
            Column {
                UsernameWithTimestamp(
                    name = model.sender?.name.toString(),
                    timestamp = model.chats.createdOn.toString()
                )
                ChatBubble(model.chats.message)
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun ReversedChatSection(model: ConsumableChatModel) {
    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
        Row(Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.End) {
            Spacer(Modifier.height(16.dp))
            Column {
                UsernameWithTimestamp(
                    name = model.sender?.name.toString(),
                    timestamp = model.chats.createdOn.toString()
                )
                ReversedChatBubble(model.chats.message)
                Spacer(Modifier.height(4.dp))
            }
            ProfilePicture(Modifier, image = model.sender?.getAvatarImage() ?: R.mipmap.ic_launcher)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewChatScreen() {
    ChattyTheme {
//        ReversedChatSection("username","8:00 PM")
    }
}