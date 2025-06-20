package com.technicology.chatty.ui.views.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.technicology.chatty.ui.components.ChatSection
import com.technicology.chatty.ui.components.EnterMessageSection
import com.technicology.chatty.ui.components.ReversedChatSection
import com.technicology.chatty.ui.theme.ChattyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(chatViewModel: ChatViewModel, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Chats",
            )
        })
    }) { padding ->
        Column(Modifier.padding(padding)) {
            ChatSection("Rakesh", " 8:00 PM")
            ReversedChatSection("You", " 9:00 PM")
            Spacer(Modifier.weight(1F))
            EnterMessageSection()
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewChatScreen() {
    ChattyTheme {
        ChatScreen(viewModel(), NavController(LocalContext.current))
    }
}