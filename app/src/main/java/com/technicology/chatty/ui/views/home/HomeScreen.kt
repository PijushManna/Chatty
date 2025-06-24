package com.technicology.chatty.ui.views.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.technicology.chatty.R
import com.technicology.chatty.ui.components.AppSearchBar
import com.technicology.chatty.ui.components.UserComponent
import com.technicology.chatty.ui.navigation.CHAT_SCREEN
import com.technicology.chatty.ui.theme.ChattyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val recipients by viewModel.recipients.collectAsStateWithLifecycle(initialValue = emptyList())
    var search by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(topBar = {
        Column {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold, fontSize = 24.sp
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                })
        }
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { }, containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.app_name),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }) { padding ->
        Column(Modifier.padding(padding)) {
            AppSearchBar(search, context)
            LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
                items(recipients) {
                    UserComponent(it) {
                        navController.navigate("$CHAT_SCREEN/${it.recipients.chatId} ")
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