package com.technicology.chatty.usecase

import com.technicology.chatty.repo.auth.AuthRepo
import com.technicology.chatty.repo.recipients.RecipientsRepo
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

class FetchRecipientsForCurrentUserUseCase @Inject constructor(val authRepo: AuthRepo, val recp: RecipientsRepo) {
    suspend fun execute(){
        recp.getAllRecipients().filterNotNull().collect {
            it.forEach {
            }
        }
    }
}