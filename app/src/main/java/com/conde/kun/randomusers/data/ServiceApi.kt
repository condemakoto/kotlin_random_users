package com.conde.kun.randomusers.data

import com.conde.kun.randomusers.data.entity.UserPageEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("/api/")
    suspend fun getUsers(@Query("page") pageNumber: Int, @Query("seed") seed: String, @Query("results") results: Int): UserPageEntity
    //fun getUsers(@Query("page") pageNumber: Int, @Query("seed") seed: String, @Query("results") results: Int): Observable<UserPageEntity>

}