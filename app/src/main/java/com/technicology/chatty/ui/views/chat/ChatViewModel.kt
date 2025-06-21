package com.technicology.chatty.ui.views.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.technicology.chatty.repo.manager.FirebaseDbManager
import com.technicology.chatty.repo.manager.FirebaseDbManager.dbRoot
import com.technicology.chatty.repo.model.ChatModel
import kotlinx.coroutines.flow.MutableStateFlow

class ChatViewModel : ViewModel() {
    val chats = MutableStateFlow<List<ChatModel>>(emptyList())

    init {
        chats.value = listOf(
            ChatModel(
                id = "1",
                message = "Sample message",
                authorId = "1"
            )
        )
    }

    fun getChats(){
        FirebaseDbManager.createNewChat()
    }

    fun getRecentChatMessages(ref: String){
        return dbRoot.child("chats").child(ref).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                chats.value = snapshot.children.map {
                    Log.d("Pijush", it.toString())
                    ChatModel(
                        id = it.key.toString(),
                        message = it.value.toString(),
                        authorId = snapshot.value.toString()
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun isAuthorMe(authorId: String) = authorId == "1"
}