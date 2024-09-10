package com.example.apiconsumption.ui.screens.beers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiconsumption.R
import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.data.viewModel.BeerViewModel
import com.example.apiconsumption.databinding.ActivityBeersBinding
import com.example.apiconsumption.ui.screens.banks.BanksActivity.Companion.USER
import com.example.apiconsumption.ui.screens.beers.rv.RvBeersAdapter
import com.example.apiconsumption.utils.loadSquareImage
import kotlinx.coroutines.launch

class BeersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeersBinding
    private lateinit var rvBeersAdapter: RvBeersAdapter
    private val beerViewModel: BeerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBeersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRv()
        initUiStateLifecycle()
        getUser()
    }

    private fun initRv() {
        lifecycleScope.launch {
            rvBeersAdapter = RvBeersAdapter()
            binding.rvBeersInformation.apply {
                layoutManager = LinearLayoutManager(this@BeersActivity)
                adapter = rvBeersAdapter
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            beerViewModel.uiState.collect{ uiState ->
                with(binding){
                    uiState.user?.let { user ->
                        tvTitleHeader.context.getString(R.string.preferred_beers, user.firstName)
                        tvUserName.text = "${user.firstName} ${user.lastName}"
                        tvUserGender.text = user.gender
                        ivUserAvatar.loadSquareImage(user.avatar)
                    }
                    if(uiState.beerList.isNotEmpty()){
                        rvBeersAdapter.beerList = uiState.beerList
                        rvBeersAdapter.notifyDataSetChanged()
                    }

                    pbBeersInformation.visibility = if(uiState.isLoadingBeers) View.VISIBLE else View.INVISIBLE
                    rvBeersInformation.visibility = if(uiState.isLoadingBeers.not()) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }

    private fun getUser(){
        val user = intent.extras?.getParcelable<User>(USER)
        user?.let {
            beerViewModel.setUser(user)
        }
    }
}