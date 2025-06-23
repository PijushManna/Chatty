package com.technicology.chatty.repo.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.technicology.chatty.repo.local.dao.UserDao
import com.technicology.chatty.repo.local.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false )
abstract class AppDatabase : RoomDatabase(){
    abstract val userDao: UserDao
}