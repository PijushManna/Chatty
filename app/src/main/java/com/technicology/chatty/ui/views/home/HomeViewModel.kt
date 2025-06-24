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
        }
//        homeScreenUserModel.value = listOf(
//            HomeScreenUserModel(
//                "1", "Rakesh", "rakes@gmail.com", "", " 7:00 AM", "is simply dummy text of the printing and typesetting industry. "
//            ),
//            HomeScreenUserModel(
//                "2", "Ritesh", "rites@gmail.com","", " 7:00 PM", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
//            ),
//            HomeScreenUserModel(
//                "3", "Tamal", "tamal@gmail.com", "", " 8:00 PM", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
//            )
//        )
    }
}