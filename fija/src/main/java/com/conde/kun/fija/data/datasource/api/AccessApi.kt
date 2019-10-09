package com.conde.kun.fija.data.datasource.api

import com.conde.kun.fija.data.datasource.remote.request.*
import com.conde.kun.fija.data.entity.LoginEntity
import com.conde.kun.fija.data.entity.UserInformationEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccessApi {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginEntity

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest)

    @GET("me")
    suspend fun getUserInfo(): UserInformationEntity
}