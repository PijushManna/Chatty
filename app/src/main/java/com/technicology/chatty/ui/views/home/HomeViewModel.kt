package com.technicology.chatty.ui.views.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.technicology.chatty.repo.manager.FirebaseDbManager.currentUser
import com.technicology.chatty.repo.manager.FirebaseDbManager.dbRoot
import com.technicology.chatty.repo.manager.FirebaseDbManager.users
import com.technicology.chatty.repo.model.HomeScreenUserModel
import com.technicology.chatty.repo.model.LastMessage
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel  : ViewModel(){
    val homeScreenUserModel = MutableStateFlow<List<HomeScreenUserModel>>(emptyList())


    init {
        getRecipients()
    }

    fun getRecipients(){
        dbRoot.child("recipients")
            .child(currentUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    homeScreenUserModel.value = snapshot.children.filter { users[it.key] != null }.map { it ->
                        val user = users[it.key]!!
                        val stamps = it.getValue(LastMessage::class.java)
                        HomeScreenUserModel(
                            id = it.key.orEmpty(),
                            name = user.name,
                            email = user.email,
                            image = user.image,
                            lastMessage = stamps?.msg.orEmpty(),
                            timeStamp = stamps?.timestamp.orEmpty(),
                            chatId = stamps?.chatId.orEmpty()
                        )
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }
}