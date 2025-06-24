package com.technicology.chatty.repo.model

import com.technicology.chatty.repo.local.entity.Chats
import com.technicology.chatty.repo.local.entity.User

data class PreviewChatModel(
    val chats: List<Chats>,
    val sender: User?,
    val receiver: User?,
)