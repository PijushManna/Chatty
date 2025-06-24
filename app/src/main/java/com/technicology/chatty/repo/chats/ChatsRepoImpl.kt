package com.technicology.chatty.repo.chats

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.technicology.chatty.repo.local.dao.ChatsDao
import com.technicology.chatty.repo.local.entity.Chats
import com.technicology.chatty.repo.model.PreviewChatModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class ChatsRepoImpl @Inject constructor(private val chatsDao: ChatsDao) : ChatsRepo {
    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val user by lazy { FirebaseAuth.getInstance().currentUser!! }
    private val chatsDb by lazy { firebaseDatabase.reference.child("chats") }
    override suspend fun fetchAllChats(chatIds: List<String>) {
        chatIds.forEach {
            chatsDb.child(it).addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(
                    snapshot: DataSnapshot, previousChildName: String?
                ) {
                    snapshot.getValue(Chats::class.java)?.also {
                        runBlocking {
                            chatsDao.insert(it)
                        }
                    }
                }

                override fun onChildChanged(
                    snapshot: DataSnapshot, previousChildName: String?
                ) {
                    snapshot.getValue(Chats::class.java)?.also {
                        runBlocking {
                            chatsDao.update(it)
                        }
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(
                    snapshot: DataSnapshot, previousChildName: String?
                ) {
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getChatsById(chatId: String): Flow<List<PreviewChatModel>?> {
       return chatsDao.getChatsById(chatId).flatMapConcat {
            var chatList = mutableListOf<Chats>()
            var previewList = mutableListOf<PreviewChatModel>()
            if (it != null) {
                var senderId = it[0].sender?.id
                for(i in it){
                    if (i.sender?.id == senderId){
                        chatList.add(i.chats)
                    }else{
                        previewList.add(
                            PreviewChatModel(
                                sender = i.sender,
                                receiver = i.receiver,
                                chats = chatList
                            )
                        )
                        chatList.clear()
                        chatList.add(i.chats)
                        senderId = i.sender?.id
                    }
                }
            }
            flow {
                emit(previewList)
            }
        }
    }

    override suspend fun addNewMessage(msg: String, toUid: String, chatId: String) {
        val ref = chatsDb.child(chatId).push()
        val newChat = Chats(
            id = ref.key.toString(),
            chatId = chatId,
            message = msg,
            receiverId = toUid,
            senderId = user.uid,
            status = "send",
            createdOn = System.currentTimeMillis()
        )

        ref.setValue(newChat)
        chatsDao.insert(newChat)
    }

    override fun isMe(uid: String): Boolean {
        return uid == user.uid
    }
}