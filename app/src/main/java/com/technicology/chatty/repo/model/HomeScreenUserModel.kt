package com.technicology.chatty.repo.model

data class HomeScreenUserModel(
    val id: String,
    var name: String,
    var email: String,
    var image: String,
    var timeStamp: String,
    var lastMessage: String,
    var chatId: String
)