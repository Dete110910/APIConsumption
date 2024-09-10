package com.example.apiconsumption.ui.screens.beers.uiState

import com.example.apiconsumption.data.api.models.Beer
import com.example.apiconsumption.data.api.models.User

data class BeersUiState(
    val user: User? = null,
    val isLoadingBeers: Boolean = true,
    val beerList: List<Beer> = emptyList()
)