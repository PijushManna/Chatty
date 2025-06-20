package com.technicology.chatty.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.technicology.chatty.R

@Composable
fun ProfilePicture(modifier: Modifier = Modifier, image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = "User logo",
        modifier = modifier
            .padding(horizontal = 8.dp)
            .size(42.dp)
            .clip(CircleShape)
            .border(1.5.dp, shape = CircleShape, color = MaterialTheme.colorScheme.secondary)
    )
}