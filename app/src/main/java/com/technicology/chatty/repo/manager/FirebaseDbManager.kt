package com.technicology.chatty.repo.manager

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.technicology.chatty.repo.model.LastMessage
import com.technicology.chatty.repo.model.User

object FirebaseDbManager{
    val dbRoot by lazy { FirebaseDatabase.getInstance().reference.root }
    val currentUser by lazy { FirebaseAuth.getInstance().currentUser!! }
    val users = mutableMapOf<String, User>()

    fun fetchUsers(){
//        snapshot.getValue(User::class.java)?.let {
//                        users[it.id] = it
//                    }
//        dbRoot.child("Users")
//            .addValueEventListener(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    Log.d("Pijush",snapshot.value.toString())
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//
//            })
        FirebaseDbManager.users.putAll(mapOf(
            "1" to User("1", "tamal", "tamal@gmail.com", "Hey, I am using Chatty", "https://picsum.photos/200/300"),
            "2" to User("2", "Rakesh","rakesh@gmail.com", "Hey, I am using Chatty", "https://picsum.photos/200/300"),
            "3" to User("3", "Ritesh","ritesh@gmail.com", "Hey, I am using Chatty", "https://picsum.photos/200/300")
        ))
    }

    fun addListener(userId: String) {
         dbRoot.child("recipients")
            .child(currentUser.uid)
            .addChildEventListener(object : ChildEventListener{
                 override fun onChildAdded(
                     snapshot: DataSnapshot,
                     previousChildName: String?
                 ) {
                     for(children in snapshot.children){
                         Log.d("Pijush",children.toString())
                     }
                 }

                 override fun onChildChanged(
                     snapshot: DataSnapshot,
                     previousChildName: String?
                 ) {
                     for(children in snapshot.children){
                         Log.d("Pijush",children.toString())
                     }
                 }

                 override fun onChildRemoved(snapshot: DataSnapshot) {
                     for(children in snapshot.children){
                         Log.d("Pijush",children.toString())
                     }
                 }

                 override fun onChildMoved(
                     snapshot: DataSnapshot,
                     previousChildName: String?
                 ) {
                     for(children in snapshot.children){
                         Log.d("Pijush",children.toString())
                     }
                 }

                 override fun onCancelled(error: DatabaseError) {
                     Log.d("Pijush",error.toString())
                 }

             })

    }

    fun addUser(user: User){
        dbRoot.child("Users")
            .child(user.id)
            .setValue(user)
    }

    fun addRecipient(userId: String){
        val newChat = createNewChat() ?: ""
        val lastMessage = LastMessage(
            msg =  "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            timestamp = "2025-06-21T20:00:00",
            chatId = newChat
        )
        dbRoot.child("recipients")
            .child(currentUser.uid)
            .child(userId)
            .setValue(lastMessage)
    }

    fun createNewChat(): String? {
        return dbRoot.child("chats")
            .push()
            .key
    }

    fun getRecentChatMessages(ref: String){
        return dbRoot.child("chats").child(ref).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.map { it.value }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun addMessage(msg: String, ref: String){
        dbRoot.child("chats").child(ref).child(currentUser.uid).push().setValue(msg)
    }
}