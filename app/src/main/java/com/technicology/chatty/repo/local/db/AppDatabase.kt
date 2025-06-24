package com.technicology.chatty.repo.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technicology.chatty.repo.local.dao.RecipientsDao
import com.technicology.chatty.repo.local.dao.UserDao
import com.technicology.chatty.repo.local.entity.Recipients
import com.technicology.chatty.repo.local.entity.User

@Database(entities = [User::class, Recipients::class], version = 2, exportSchema = false )
abstract class AppDatabase : RoomDatabase(){
    abstract val userDao: UserDao
    abstract val recipientsDao: RecipientsDao
}