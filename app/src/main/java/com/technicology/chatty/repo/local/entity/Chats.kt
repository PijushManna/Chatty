package com.technicology.chatty.repo.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("chats")
data class Chats(
    @PrimaryKey
    val id: String,
    var chatId: String,
    var message: String,
    var senderId: String,
    var receiverId: String,
    var status: String,
    var createdOn: Long
){
    constructor() : this("", "","","","","",0L)
}