package com.example.apiconsumption.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiconsumption.data.api.models.Bank
import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.data.api.retrofit.RetrofitService
import com.example.apiconsumption.ui.screens.banks.uiState.BanksUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BankViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BanksUiState())
    val uiState: StateFlow<BanksUiState> = _uiState.asStateFlow()

    private lateinit var user: User

    private val retrofitApi by lazy { RetrofitService.getInstance() }

    private fun getUserInformation() {
        viewModelScope.launch {
            val newUiState = _uiState.value.copy(
                user = user,
                isLoadingBanks = true
            )
            _uiState.value = newUiState
            getBanks()
        }
    }

    private suspend fun getBanks() {
        val banks = arrayListOf<Bank>()
        for (i in 1..2) {
            delay(2500)
            banks.add(retrofitApi.getBanks())
        }
        _uiState.update {
            _uiState.value.copy(
                bankList = banks,
                isLoadingBanks = false
            )
        }
    }

    fun setUser(user: User) {
        if (uiState.value.user != user) {
            this.user = user
            getUserInformation()
        }
    }
}