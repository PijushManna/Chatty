package com.technicology.chatty.repo.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.technicology.chatty.repo.local.base.BaseDao
import com.technicology.chatty.repo.local.entity.Chats
import com.technicology.chatty.repo.model.ConsumableChatModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatsDao : BaseDao<Chats> {
    @Transaction
    @Query("SELECT * FROM chats WHERE chatId =:chatId ORDER BY createdOn")
    fun getChatsById(chatId: String):Flow<List<ConsumableChatModel>?>
}