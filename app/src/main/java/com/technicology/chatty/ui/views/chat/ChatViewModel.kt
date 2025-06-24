package com.technicology.chatty.ui.views.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicology.chatty.repo.chats.ChatsRepo
import com.technicology.chatty.repo.model.ConsumableChatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatsRepo: ChatsRepo) : ViewModel() {
    private val _chats = MutableStateFlow<List<ConsumableChatModel>>(emptyList())
    val chats: StateFlow<List<ConsumableChatModel>> = _chats
    var chatId: String = ""

    init {
        viewModelScope.launch {
            chatsRepo.fetchAllChats(emptyList())
        }
    }

    fun getChatsFromId(id: String){
        viewModelScope.launch {
            chatId = id
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

    fun addMessage(msg:String){
        viewModelScope.launch {
            chatsRepo.addNewMessage(msg, "JVtkdrowZYdhdexW4Ax8DRv51dD3", chatId)
        }
    }
}