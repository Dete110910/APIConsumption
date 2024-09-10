package com.example.apiconsumption.data.viewModel;

import com.example.apiconsumption.ui.screens.users.uiState.UsersUiState;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope

import com.example.apiconsumption.data.api.retrofit.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel: ViewModel(){

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getUsers()
        }
    }

    private val retrofit by lazy {
        RetrofitService.getInstance()
    }

    private suspend fun getUsers(){
        val users = retrofit.getUsers(15, true)
        _uiState.value = _uiState.value.copy(
            users = users,
            isLoading = false
        )
    }
}