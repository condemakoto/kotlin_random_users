package com.conde.kun.fija.data.datasource.remote

import com.conde.kun.fija.data.datasource.AccessDataSource
import com.conde.kun.fija.data.datasource.api.AccessApi
import com.conde.kun.fija.data.datasource.api.ServiceApiConfigurator
import com.conde.kun.fija.data.datasource.remote.request.RegisterRequest
import com.conde.kun.fija.data.datasource.remote.request.*
import com.conde.kun.fija.data.entity.LoginEntity
import com.conde.kun.fija.data.entity.UserInformationEntity

class RemoteAccessDataSource(val accessApi: AccessApi) : AccessDataSource {

    override suspend fun login(useraccount: String, password: String): LoginEntity {
        return accessApi.login(LoginRequest(useraccount, password))
    }

    override suspend fun register(
        useraccount: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        val request = RegisterRequest(useraccount, password, firstName, lastName, 1)
        accessApi.register(request)
    }

    override suspend fun getUserInfo(): UserInformationEntity {
        return accessApi.getUserInfo()
    }
}