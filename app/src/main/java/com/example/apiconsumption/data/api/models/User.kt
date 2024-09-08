package com.example.apiconsumption.data.api.models

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String,
    val gender: String
)
