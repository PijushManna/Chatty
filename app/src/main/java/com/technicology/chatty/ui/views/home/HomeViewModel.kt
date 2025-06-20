package com.technicology.chatty.ui.views.home

import androidx.lifecycle.ViewModel
import com.technicology.chatty.repo.model.HomeScreenUserModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel  : ViewModel(){
    val homeScreenUserModel = MutableStateFlow<List<HomeScreenUserModel>>(emptyList())

    init {
        homeScreenUserModel.value = listOf(
            HomeScreenUserModel(
                "1", "Rakesh", "rakes@gmail.com", "", " 7:00 AM", "is simply dummy text of the printing and typesetting industry. "
            ),
            HomeScreenUserModel(
                "2", "Ritesh", "rites@gmail.com","", " 7:00 PM", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            ),
            HomeScreenUserModel(
                "3", "Tamal", "tamal@gmail.com", "", " 8:00 PM", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
            )
        )
    }
}