package com.technicology.chatty.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.technicology.chatty.R
import com.technicology.chatty.repo.model.HomeScreenUserModel

@Composable
fun UserComponent(user:HomeScreenUserModel, onClick:(HomeScreenUserModel)-> Unit) {
    Row(Modifier.fillMaxWidth().padding(8.dp).clickable{
        onClick(user)
    }) {
        ProfilePicture(Modifier, image = user.image)
        Spacer(Modifier.width(8.dp))
        Column {
            UsernameWithTimestamp(name = user.name, timestamp = user.timeStamp)
            Text(text = user.lastMessage, maxLines = 1 , overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(end = 16.dp))
        }
    }
}