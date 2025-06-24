package com.technicology.chatty.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppSearchBar(search: String, context: Context) {
    var search1 = search
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(query = search1, onQueryChange = {
                search1 = it
            }, onSearch = {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }, expanded = false, onExpandedChange = {

            }, leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }, trailingIcon = {
                if (search1.isNotBlank()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            search1 = ""
                        })
                }
            }, placeholder = {
                Text("Type here to search")
            })
        }, expanded = false, onExpandedChange = {

        }, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) { }
}