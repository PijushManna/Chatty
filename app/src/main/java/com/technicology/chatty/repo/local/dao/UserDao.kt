package com.technicology.chatty.repo.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.technicology.chatty.repo.local.base.BaseDao
import com.technicology.chatty.repo.local.entity.User

@Dao
interface UserDao : BaseDao<User>{
    @Query("SELECT * FROM users WHERE isCurrentUser = 1")
    suspend fun getCurrentUser(): User

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}