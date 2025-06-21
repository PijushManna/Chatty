package com.technicology.chatty.ui.views.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.technicology.chatty.repo.manager.FirebaseDbManager
import com.technicology.chatty.ui.navigation.AppNavController
import com.technicology.chatty.ui.theme.ChattyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseDbManager.fetchUsers()
        setContent {
            ChattyTheme {
                AppNavController()
            }
        }
    }
}
