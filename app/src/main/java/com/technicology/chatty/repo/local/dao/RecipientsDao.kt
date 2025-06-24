package com.technicology.chatty.repo.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.technicology.chatty.repo.local.base.BaseDao
import com.technicology.chatty.repo.local.entity.Recipients
import com.technicology.chatty.repo.model.ConsumableRecipientModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipientsDao : BaseDao<Recipients> {
    @Transaction
    @Query("SELECT * FROM recipients")
    fun getAllRecipients(): Flow<List<ConsumableRecipientModel>?>

    @Query("DELETE FROM recipients")
    suspend fun deleteAllRecipients()
}