package com.technicology.chatty.repo.model

data class User(
    var id: String,
    var name: String,
    var email: String,
    var statusMessage: String,
    var image: String
)
