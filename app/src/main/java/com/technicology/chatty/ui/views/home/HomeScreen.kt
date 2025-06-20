package com.technicology.chatty.ui.views.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.technicology.chatty.ui.components.UserComponent
import com.technicology.chatty.ui.navigation.CHAT_SCREEN
import com.technicology.chatty.ui.theme.ChattyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val users = viewModel.homeScreenUserModel.collectAsStateWithLifecycle()
    var search by remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Chatty")
                })
        }) { padding ->
        Column(Modifier.padding(padding)) {
            SearchBar(inputField = {
                SearchBarDefaults.InputField(query = search, onQueryChange = {
                    search = it
                }, onSearch = {
                    Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
                }, expanded = false, onExpandedChange = {

                }, leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.padding(horizontal = 8.dp))
                }, trailingIcon = {
                    if(search.isNotBlank()) {
                        Icon(Icons.Default.Clear, contentDescription = null, modifier = Modifier.clickable{
                            search = ""
                        })
                    }
                }, placeholder = {
                    Text("Type here to search")
                })
            } , expanded = false, onExpandedChange = {

            } , modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            ) { }
            LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
                items(users.value) {
                    UserComponent(it){
                        navController.navigate(CHAT_SCREEN)
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    ChattyTheme {
        HomeScreen(viewModel(), NavController(LocalContext.current))
    }
}