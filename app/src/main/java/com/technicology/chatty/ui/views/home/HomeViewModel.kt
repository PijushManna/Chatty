package com.technicology.chatty.ui.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicology.chatty.repo.auth.AuthRepo
import com.technicology.chatty.repo.model.HomeScreenUserModel
import com.technicology.chatty.repo.recipients.RecipientsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel  @Inject constructor(private val repo: RecipientsRepo, private val authRepo: AuthRepo): ViewModel(){
    val homeScreenUserModel = MutableStateFlow<List<HomeScreenUserModel>>(emptyList())
    val recipients = repo.getAllRecipients().filterNotNull()
    init {
        viewModelScope.launch {
            try {
                authRepo.getCurrentUser()
                authRepo.updateCurrentUserInLocal()
            }catch (e:Exception){

            }

//            repo.addRecipient("9N9JJeMUpCYXevUBjL7OcI3amij1")
//            repo.addRecipient("UzXhsluZrQS2NONeWs6ziEuH0DC2")
//            repo.addRecipient("qskP7AL1G2dC2WuN4EXsbdiGUwl1")
        }
    }
}