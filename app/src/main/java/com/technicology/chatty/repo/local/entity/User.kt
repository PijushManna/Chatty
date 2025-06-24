package com.technicology.chatty.repo.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.technicology.chatty.utils.SessionData

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    var name: String,
    var email: String,
    var phone: String,
    var about: String,
    var image: String,
    var avatar: Int,
    var status: String,
    var isCurrentUser: Boolean
){
    constructor() : this("","","","","","",0,"",true)
    fun getAvatarImage() = SessionData.avatars[avatar]
}