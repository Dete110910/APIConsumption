package com.example.apiconsumption

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiconsumption.data.viewModel.UsersViewModel
import com.example.apiconsumption.databinding.ActivityMainBinding
import com.example.apiconsumption.ui.screens.users.rv.RvUsersAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUsersAdapter: RvUsersAdapter
    private val userViewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRv()
        initUiStateLifecycle()
    }

    private fun initRv() {
        rvUsersAdapter = RvUsersAdapter()
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvUsersAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            userViewModel.uiState.collect { uiState ->
                if (uiState.users.isNotEmpty()) {
                    rvUsersAdapter.users = uiState.users
                    Log.d("TEST--", uiState.users.toString())
                    rvUsersAdapter.notifyDataSetChanged()
                }
                binding.rvUsers.visibility = if (uiState.isLoading) View.INVISIBLE else View.VISIBLE
                binding.pbUsers.visibility = if (uiState.isLoading.not()) View.INVISIBLE else View.VISIBLE
            }
        }
    }

}