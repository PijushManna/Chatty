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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.technicology.chatty.repo.manager.FirebaseDbManager
import com.technicology.chatty.ui.components.ChatSection
import com.technicology.chatty.ui.components.EnterMessageSection
import com.technicology.chatty.ui.components.ReversedChatSection
import com.technicology.chatty.ui.theme.ChattyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(chatViewModel: ChatViewModel, navController: NavController, chatId: String?) {
    val chats = chatViewModel.chats.collectAsStateWithLifecycle()
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Chats",
            )
        })
    }) { padding ->
        Column(Modifier.padding(padding)) {
//            ChatSection("Rakesh", " 8:00 PM", "https://picsum.photos/200/300")

            ReversedChatSection("You", " 9:00 PM", "https://picsum.photos/200/300", chats.value.get(0).message)
            Spacer(Modifier.weight(1F))
            EnterMessageSection{
                if (chatId.isNullOrBlank().not()) {
                    FirebaseDbManager.addMessage(it, chatId)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewChatScreen() {
    ChattyTheme {
        ChatScreen(viewModel(), NavController(LocalContext.current), "chatId")
    }
}