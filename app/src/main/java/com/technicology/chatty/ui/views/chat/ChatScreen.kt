package com.technicology.chatty.ui.views.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.technicology.chatty.ui.components.ChatSection
import com.technicology.chatty.ui.components.EnterMessageSection
import com.technicology.chatty.ui.components.ReversedChatSection
import com.technicology.chatty.ui.theme.ChattyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(chatViewModel: ChatViewModel, navController: NavController, chatId: String?) {
    val chats = chatViewModel.chats.collectAsStateWithLifecycle(initialValue = emptyList())

    chatViewModel.getChatsFromId(chatId)
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Chats",
            )
        })
    }) { padding ->
        Column(Modifier.padding(padding)) {
            LazyColumn {
                items(chats.value) {
                    if (chatViewModel.isMe(it.sender?.id)) {
                        ReversedChatSection(it)
                    } else {
                        ChatSection(
                            it
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1F))
            EnterMessageSection()
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewChatScreen() {
    ChattyTheme {
        ChatScreen(viewModel(), NavController(LocalContext.current), "")
    }
}