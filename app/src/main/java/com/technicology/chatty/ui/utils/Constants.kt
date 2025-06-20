package com.technicology.chatty.ui.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object Constants {
    val BubbleShape = RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    val ReversedBubbleShape = RoundedCornerShape(topEnd = 4.dp, topStart = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
}