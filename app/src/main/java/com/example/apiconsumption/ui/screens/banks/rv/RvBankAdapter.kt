package com.example.apiconsumption.ui.screens.banks.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiconsumption.data.api.models.Bank
import com.example.apiconsumption.databinding.BankViewBinding

class RvBankAdapter() : RecyclerView.Adapter<BankViewHolder>() {

    var bankList = emptyList<Bank>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val binding = BankViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.bind(bankList[position])
    }

    override fun getItemCount(): Int = bankList.size
}