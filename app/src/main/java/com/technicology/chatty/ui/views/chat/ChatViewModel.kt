package com.technicology.chatty.ui.views.chat

import androidx.lifecycle.ViewModel
import com.technicology.chatty.repo.model.ChatModel
import kotlinx.coroutines.flow.MutableStateFlow

class ChatViewModel : ViewModel() {
    val chats = MutableStateFlow<List<ChatModel>>(emptyList())

    init {
        chats.value = listOf(
            ChatModel(
                id = "1",
                message = "Sample message",
                authorId = "1"
            )
        )
    }

    fun isAuthorMe(authorId: String) = authorId == "1"
}