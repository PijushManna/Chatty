package com.technicology.chatty.repo.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technicology.chatty.repo.local.dao.ChatsDao
import com.technicology.chatty.repo.local.dao.RecipientsDao
import com.technicology.chatty.repo.local.dao.UserDao
import com.technicology.chatty.repo.local.entity.Chats
import com.technicology.chatty.repo.local.entity.Recipients
import com.technicology.chatty.repo.local.entity.User

@Database(entities = [User::class, Recipients::class, Chats::class], version = 3, exportSchema = false )
abstract class AppDatabase : RoomDatabase(){
    abstract val userDao: UserDao
    abstract val recipientsDao: RecipientsDao
    abstract val chatsDao: ChatsDao
}