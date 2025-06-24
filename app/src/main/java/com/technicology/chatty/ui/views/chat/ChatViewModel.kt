package com.technicology.chatty.ui.views.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicology.chatty.repo.chats.ChatsRepo
import com.technicology.chatty.repo.model.PreviewChatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatsRepo: ChatsRepo) : ViewModel() {
    private val _chats = MutableStateFlow<List<PreviewChatModel>>(emptyList())
    val chats: StateFlow<List<PreviewChatModel>> = _chats
    fun getChatsFromId(id: String?){
        viewModelScope.launch {
            if (id.isNullOrBlank().not())
            chatsRepo.getChatsById(id).collect {
                if (it != null) {
                    _chats.value = it
                }
           }
        }
    }

    fun isMe(uid: String?): Boolean {
        if (uid.isNullOrBlank()) return false
        return chatsRepo.isMe(uid)
    }
}