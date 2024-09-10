package com.example.apiconsumption.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiconsumption.data.api.models.Beer
import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.data.api.retrofit.RetrofitService
import com.example.apiconsumption.ui.screens.beers.uiState.BeersUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(BeersUiState())
    val uiState: StateFlow<BeersUiState> = _uiState.asStateFlow()

    private lateinit var user: User

    private val retrofitApi by lazy { RetrofitService.getInstance() }

    private fun getUserInformation() {
        viewModelScope.launch {
            val newUiState = _uiState.value.copy(
                user = user,
            )
            _uiState.value = newUiState

            getBeers()
        }
    }

    private suspend fun getBeers() {
        val beers = mutableListOf<Beer>()
        for (i in 1..5) {
            beers.add(retrofitApi.getBeers())
            delay(2500)
        }
        _uiState.update {
            _uiState.value.copy(
                isLoadingBeers = false,
                beerList = beers
            )
        }
    }

    fun setUser(user: User) {
        this.user = user
        getUserInformation()
    }
}