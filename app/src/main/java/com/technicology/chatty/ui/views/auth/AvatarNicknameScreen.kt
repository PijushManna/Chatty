package com.technicology.chatty.ui.views.auth

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technicology.chatty.R
import com.technicology.chatty.ui.theme.ChattyTheme
import com.technicology.chatty.ui.theme.PillShape
import com.technicology.chatty.utils.SessionData

@Composable
fun AvatarNicknameScreen(
    onContinueClick: (nickname: String, about: String, avatar: Int) -> Unit
) {
    val context = LocalContext.current
    var nickname: String by rememberSaveable { mutableStateOf("") }
    var about: String by rememberSaveable { mutableStateOf(context.getString(R.string.hey_there_i_am_using_chatty)) }
    var avatar: Int by rememberSaveable { mutableIntStateOf(0) }

    val spacing = dimensionResource(id = R.dimen.spacing_large)
    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacing),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.pick_avatar),
            style = typography.headlineLarge.copy(fontSize = 40.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xxlarge)))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(SessionData.avatars.size) { index ->
                GetAvatar(SessionData.avatars[index], isSelected = avatar == index) {
                    avatar = index
                }
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xxlarge)))

        OutlinedTextField(
            value = nickname,
            onValueChange = {
                nickname = it
            },
            label = { Text(stringResource(R.string.label_nickname)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = PillShape
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))

        OutlinedTextField(
            value = about,
            onValueChange = {
                about = it
            },
            label = { Text(stringResource(R.string.about)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = PillShape
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_large)))
        Button(
            onClick = {
                onContinueClick(nickname, about, avatar)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.button_height))
        ) {
            Text(text = stringResource(R.string.continue_btn))
        }
    }
}

@Composable
private fun GetAvatar(@DrawableRes image: Int, isSelected: Boolean, onAvatarClick: () -> Unit) {
    val modifier = if (isSelected) {
        Modifier
            .clip(CircleShape)
            .size(60.dp)
            .clickable { onAvatarClick() }
            .border(3.dp, color = Color.Green, shape = CircleShape)
    } else {
        Modifier
            .clip(CircleShape)
            .size(60.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onAvatarClick() }
    }

    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(R.string.avatar_desc),
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAvatar() {
    ChattyTheme {
        AvatarNicknameScreen { _, _, _ -> }
    }
}