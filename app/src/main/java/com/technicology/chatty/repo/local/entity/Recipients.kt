package com.technicology.chatty.repo.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipients")
data class Recipients(
    @PrimaryKey
    val id: String,
    var uid: String,
    var chatId: String,
    var lastSeen: String,
    var lastMessage: String
){
    constructor() : this("","","","","")
}