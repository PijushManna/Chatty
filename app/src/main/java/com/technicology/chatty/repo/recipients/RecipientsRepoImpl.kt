package com.technicology.chatty.repo.recipients

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.technicology.chatty.repo.local.dao.RecipientsDao
import com.technicology.chatty.repo.local.dao.UserDao
import com.technicology.chatty.repo.local.entity.Recipients
import com.technicology.chatty.repo.local.entity.User
import com.technicology.chatty.repo.model.ConsumableRecipientModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class RecipientsRepoImpl @Inject constructor(private val recipientsDao: RecipientsDao, private val userDao: UserDao) :
    RecipientsRepo {
    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val firebaseUser by lazy { FirebaseAuth.getInstance().currentUser }
    override fun getAllRecipients(): Flow<List<ConsumableRecipientModel>?> {
        fetchRecipients()
        return recipientsDao.getAllRecipients()
    }

    fun fetchRecipients() {
        firebaseDatabase.reference.child("recipients").child(firebaseUser?.uid.orEmpty())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    runBlocking {
                        snapshot.children.mapNotNull { it.getValue(Recipients::class.java)?.let{
                            storeUserInLocalDb(it.uid)
                            it
                        }

                        }.let {
                            recipientsDao.insert(it)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    fun storeUserInLocalDb(uid: String){
        firebaseDatabase.reference.child("app_users").child(uid).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                runBlocking {
                    snapshot.getValue(User::class.java)?.let {
                        userDao.insert(it)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    override fun addRecipient(uid: String) {
        val ref = firebaseDatabase.reference.child("recipients").child(firebaseUser?.uid.orEmpty())
            .child(uid)
        val chatRef = initChat()
        ref.setValue(
            Recipients(
                id = ref.key.toString(),
                uid = uid,
                chatId = chatRef,
                lastMessage = "Sample message",
                lastSeen = " 8:00 PM"
            )
        )
    }

    fun initChat(): String {
        val ref = firebaseDatabase.reference.child("chats").push().key
        return ref.orEmpty()
    }
}