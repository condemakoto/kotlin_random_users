package com.conde.kun.fija.data.entity

import retrofit2.http.GET

interface FactApi {

    @GET("facts")
    suspend fun getFacts(): FactListEntity

}