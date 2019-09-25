package com.conde.kun.randomusers.data

import android.util.Log
import com.conde.kun.randomusers.data.entity.UserPageEntity
import io.reactivex.Observable

class ServiceFacade {

    lateinit var serviceApi: ServiceApi
    val API_BASE_URL: String = "https://randomuser.me"
    val USER_AMOUNT_PER_PAGE: Int = 50

    init {
        Log.d("JLK", "constructor de service facade worked")
    }

    fun getUsers(page: Int, seed: String): Observable<UserPageEntity> {
        return serviceApi.getUsers(page, seed, USER_AMOUNT_PER_PAGE)
    }

}