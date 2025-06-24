package com.technicology.chatty.repo.chats

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.technicology.chatty.repo.local.dao.ChatsDao
import com.technicology.chatty.repo.local.entity.Chats
import com.technicology.chatty.repo.model.ConsumableChatModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class ChatsRepoImpl @Inject constructor(private val chatsDao: ChatsDao) : ChatsRepo {
    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val user by lazy { FirebaseAuth.getInstance().currentUser!! }
    private val chatsDb by lazy { firebaseDatabase.reference.child("chats") }
    override suspend fun fetchAllChats(chatIds: List<String>) {
//        chatIds.forEach {
//            chatsDb.child(it).addChildEventListener(object : ChildEventListener {
//                override fun onChildAdded(
//                    snapshot: DataSnapshot, previousChildName: String?
//                ) {
//                    snapshot.getValue(Chats::class.java)?.also {
//                        runBlocking {
//                            chatsDao.insert(it)
//                        }
//                    }
//                }
//
//                override fun onChildChanged(
//                    snapshot: DataSnapshot, previousChildName: String?
//                ) {
//                    snapshot.getValue(Chats::class.java)?.also {
//                        runBlocking {
//                            chatsDao.update(it)
//                        }
//                    }
//                }
//
//                override fun onChildRemoved(snapshot: DataSnapshot) {
//
//                }
//
//                override fun onChildMoved(
//                    snapshot: DataSnapshot, previousChildName: String?
//                ) {
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                }
//
//            })
//        }
         listOf(
            Chats(
                id = "1",
                chatId = "-OTXi4YUyQnV_FU7GYqw",
                message = "Hey, how are you?",
                senderId = "9N9JJeMUpCYXevUBjL7OcI3amij1",
                receiverId = "UzXhsluZrQS2NONeWs6ziEuH0DC2",
                status = "sent",
                createdOn = System.currentTimeMillis() - 1000000
            ),
            Chats(
                id = "2",
                chatId = "-OTXi4YW9j-BYha3s9lo",
                message = "I'm good! You?",
                senderId = "UzXhsluZrQS2NONeWs6ziEuH0DC2",
                receiverId = "9N9JJeMUpCYXevUBjL7OcI3amij1",
                status = "delivered",
                createdOn = System.currentTimeMillis() - 900000
            ),
            Chats(
                id = "3",
                chatId = "-OTXi4YXGACOd2DpYZ-w",
                message = "Let’s catch up later.",
                senderId = "qskP7AL1G2dC2WuN4EXsbdiGUwl1",
                receiverId = "9N9JJeMUpCYXevUBjL7OcI3amij1",
                status = "read",
                createdOn = System.currentTimeMillis() - 800000
            ),
            Chats(
                id = "4",
                chatId = "-OTXi4YUyQnV_FU7GYqw",
                message = "Sure, what time?",
                senderId = "9N9JJeMUpCYXevUBjL7OcI3amij1",
                receiverId = "qskP7AL1G2dC2WuN4EXsbdiGUwl1",
                status = "sent",
                createdOn = System.currentTimeMillis() - 700000
            ),
            Chats(
                id = "5",
                chatId = "-OTXi4YW9j-BYha3s9lo",
                message = "Meeting at 3 PM?",
                senderId = "UzXhsluZrQS2NONeWs6ziEuH0DC2",
                receiverId = "qskP7AL1G2dC2WuN4EXsbdiGUwl1",
                status = "delivered",
                createdOn = System.currentTimeMillis() - 600000
            ),
            Chats(
                id = "6",
                chatId = "-OTXi4YXGACOd2DpYZ-w",
                message = "Perfect. See you then.",
                senderId = "qskP7AL1G2dC2WuN4EXsbdiGUwl1",
                receiverId = "UzXhsluZrQS2NONeWs6ziEuH0DC2",
                status = "read",
                createdOn = System.currentTimeMillis() - 500000
            ),
            Chats(
                id = "7",
                chatId = "-OTXi4YUyQnV_FU7GYqw",
                message = "Don’t forget the files.",
                senderId = "9N9JJeMUpCYXevUBjL7OcI3amij1",
                receiverId = "UzXhsluZrQS2NONeWs6ziEuH0DC2",
                status = "sent",
                createdOn = System.currentTimeMillis() - 400000
            ),
            Chats(
                id = "8",
                chatId = "-OTXi4YW9j-BYha3s9lo",
                message = "Got them all ready!",
                senderId = "UzXhsluZrQS2NONeWs6ziEuH0DC2",
                receiverId = "9N9JJeMUpCYXevUBjL7OcI3amij1",
                status = "read",
                createdOn = System.currentTimeMillis() - 300000
            )
        ).let {
            chatsDao.insert(it)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getChatsById(chatId: String): Flow<List<ConsumableChatModel>?> {
       return chatsDao.getChatsById(chatId)
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