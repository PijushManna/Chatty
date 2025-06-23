package com.technicology.chatty.ui.views.auth

sealed class AuthUiState {
    data object None : AuthUiState()
    data object InProgress : AuthUiState()
    data object Successful : AuthUiState()
    data class Failed(val error: String) : AuthUiState()
}