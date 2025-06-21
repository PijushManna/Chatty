package com.technicology.chatty.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ProfilePicture(modifier: Modifier = Modifier, image: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(image).build(),
        contentDescription = "User logo",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .size(42.dp)
            .clip(CircleShape)
            .border(1.5.dp, shape = CircleShape, color = MaterialTheme.colorScheme.secondary)
    )
}