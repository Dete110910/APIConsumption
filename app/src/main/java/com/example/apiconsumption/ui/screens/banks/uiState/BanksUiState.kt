package com.example.apiconsumption.ui.screens.banks.uiState

import com.example.apiconsumption.data.api.models.Bank
import com.example.apiconsumption.data.api.models.User

data class BanksUiState(
    val user: User? = null,
    val isLoadingBanks: Boolean = true,
    val bankList: List<Bank> = emptyList()
) {
}