package com.example.apiconsumption.data.api.retrofit

import com.example.apiconsumption.data.api.models.Bank
import com.example.apiconsumption.data.api.models.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("size") size: Int,
        @Query("is_xml") isXml: Boolean
    ): List<User>

    @GET("banks")
    suspend fun getBanks(): Bank

}
