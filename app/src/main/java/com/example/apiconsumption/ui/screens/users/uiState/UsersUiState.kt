package com.example.apiconsumption.ui.screens.users.uiState

import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.data.api.models.Users

data class UsersUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = true
) {
}