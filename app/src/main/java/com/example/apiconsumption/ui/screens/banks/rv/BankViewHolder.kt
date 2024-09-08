package com.example.apiconsumption.ui.screens.banks.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.apiconsumption.data.api.models.Bank
import com.example.apiconsumption.databinding.BankViewBinding

class BankViewHolder(
    private val binding: BankViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(bank: Bank) {
        with(binding){
            tvBankId.text = bank.id.toString()
            tvBankName.text = bank.bankName
            tvBankAccountNumber.text = bank.accountNumber
        }
    }
}