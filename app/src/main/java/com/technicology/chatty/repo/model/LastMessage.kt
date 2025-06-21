package com.technicology.chatty.repo.model

data class LastMessage(
    val msg: String,
    val timestamp: String,
    val chatId: String
){
    constructor() : this("","", "")
}