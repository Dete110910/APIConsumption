package com.example.apiconsumption.ui.screens.banks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiconsumption.R
import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.data.viewModel.BankViewModel
import com.example.apiconsumption.databinding.ActivityBanksBinding
import com.example.apiconsumption.ui.screens.banks.rv.RvBankAdapter
import com.example.apiconsumption.utils.loadSquareImage
import kotlinx.coroutines.launch

class BanksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBanksBinding
    private lateinit var rvBankAdapter: RvBankAdapter
    private val bankViewModel: BankViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBanksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRv()
        initUiStateLifecycle()
        getUser()
    }

    private fun initRv() {
        rvBankAdapter = RvBankAdapter()
        binding.rvBanksInformation.apply {
            layoutManager = LinearLayoutManager(this@BanksActivity)
            adapter = rvBankAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            bankViewModel.uiState.collect { uiState ->
                with(binding) {
                    uiState.user?.let { user ->
                        tvUserName.text = "${user.firstName} ${user.lastName}"
                        tvUserEmail.text = user.email
                        tvTitleHeader.text = tvTitleHeader.context.getString(
                            R.string.subscribed_banks_by,
                            user.firstName
                        )
                        ivUserAvatar.loadSquareImage(user.avatar)
                    }
                    if (uiState.bankList.isNotEmpty()) {
                        rvBankAdapter.bankList = uiState.bankList
                        rvBankAdapter.notifyDataSetChanged()
                    }
                    pbBanksInformation.visibility =
                        if (uiState.isLoadingBanks) View.VISIBLE else View.INVISIBLE
                    rvBanksInformation.visibility =
                        if (uiState.isLoadingBanks.not()) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }

    private fun getUser() {
        val user = intent.extras?.getParcelable<User>(USER)
        user?.let {
            bankViewModel.setUser(user)
        }
    }

    companion object {
        const val USER = "user"
    }
}