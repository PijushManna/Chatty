package com.technicology.chatty.repo.chats

import com.technicology.chatty.repo.model.ConsumableChatModel
import com.technicology.chatty.repo.model.PreviewChatModel
import kotlinx.coroutines.flow.Flow

interface ChatsRepo {
    suspend fun fetchAllChats(ids: List<String>)
    suspend fun getChatsById(chatId: String): Flow<List<PreviewChatModel>?>
    suspend fun addNewMessage(msg: String, toUid: String, chatId: String)
    fun isMe(uid: String): Boolean
}