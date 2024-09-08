package com.example.apiconsumption.data.api.retrofit

import com.example.apiconsumption.data.api.models.User
import com.example.apiconsumption.data.api.models.Users
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("size") size: Int,
        @Query("is_xml") isXml: Boolean
    ): List<User>

}
