package com.example.apiconsumption.data.viewModel

import android.util.Log
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

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    private fun getUserInformation() {
        viewModelScope.launch {
            val newUiState = _uiState.value.copy(
                user = user,
                bankList = emptyList(),
                isLoadingBanks = true
            )
            _uiState.value = newUiState

            getBanks()
        }
    }

    private suspend fun getBanks() {
        val banks = mutableListOf<Bank>()
        for (i in 1..2) {
            delay(2500)
            banks.add(retrofitApi.getBanks())
        }
        Log.d("TEST--", banks.toString())
        _uiState.update {
            _uiState.value.copy(
                bankList = banks,
                isLoadingBanks = false
            )
        }


    }

    fun setUser(user: User) {
        this.user = user
        getUserInformation()
    }
}