package com.technicology.chatty.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.technicology.chatty.repo.validations.Validator.containsDigits
import com.technicology.chatty.repo.validations.Validator.containsLowerCase
import com.technicology.chatty.repo.validations.Validator.containsSymbol
import com.technicology.chatty.repo.validations.Validator.containsUpperCase
import com.technicology.chatty.repo.validations.Validator.isLenValid

@Composable
fun PasswordValidatorSection(password: String) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Password must contain : ", style = TextStyle.Default)
        Text("At least 6 characters", style = getStyle(isLenValid(password)))
        Text("1 Capital Letter", style = getStyle(containsUpperCase(password)))
        Text("1 Small Letter", style = getStyle(containsLowerCase(password)))
        Text("1 Symbol", style = getStyle(containsSymbol(password)))
        Text("1 Numeric Character", style = getStyle(containsDigits(password)))
    }
}

private fun getStyle(value: Boolean): TextStyle {
    return if(value) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle.Default
}