package com.technicology.chatty.repo.recipients

import com.technicology.chatty.repo.model.ConsumableRecipientModel
import kotlinx.coroutines.flow.Flow

interface RecipientsRepo{
    fun getAllRecipients(): Flow<List<ConsumableRecipientModel>?>
    fun addRecipient(uid: String)
}