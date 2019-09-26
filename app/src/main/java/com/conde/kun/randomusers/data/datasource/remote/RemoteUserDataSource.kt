package com.conde.kun.randomusers.data.datasource.remote

import com.conde.kun.randomusers.data.ServiceApi
import com.conde.kun.randomusers.data.ServiceApiConfigurator
import com.conde.kun.randomusers.data.datasource.UserDataSource
import com.conde.kun.randomusers.data.entity.UserPageEntity
import java.util.*

class RemoteUserDataSource : UserDataSource {

    private val USER_AMOUNT_PER_PAGE: Int = 50
    private val SEED: String = Calendar.getInstance().timeInMillis.toString()

    private val serviceApi: ServiceApi by lazy {
        ServiceApiConfigurator().serviceApi
    }

    override suspend fun getUsers(page: Int): UserPageEntity {
        return serviceApi.getUsers(page, SEED, USER_AMOUNT_PER_PAGE)
    }

}