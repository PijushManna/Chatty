package com.technicology.chatty.repo.model

import androidx.room.Embedded
import androidx.room.Relation
import com.technicology.chatty.repo.local.entity.Recipients
import com.technicology.chatty.repo.local.entity.User

data class ConsumableRecipientModel(
    @Embedded val recipients: Recipients,
    @Relation(
        parentColumn = "uid",
        entityColumn = "id"
    )
    var user: User?
)