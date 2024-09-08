package com.example.apiconsumption.data.api.models

import com.google.gson.annotations.SerializedName

data class Bank(
    val id: Int,
    @SerializedName("account_number")
    val accountNumber: String,
    @SerializedName("bank_name")
    val bankName: String
)
