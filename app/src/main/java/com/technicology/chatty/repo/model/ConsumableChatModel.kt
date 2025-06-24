package com.technicology.chatty.repo.model

import androidx.room.Embedded
import androidx.room.Relation
import com.technicology.chatty.repo.local.entity.Chats
import com.technicology.chatty.repo.local.entity.User

data class ConsumableChatModel(
    @Embedded val chats: Chats,
    @Relation(
        parentColumn = "senderId", entityColumn = "id"
    ) val sender: User?,
    @Relation(
        parentColumn = "receiverId", entityColumn = "id"
    ) val receiver: User?,
)