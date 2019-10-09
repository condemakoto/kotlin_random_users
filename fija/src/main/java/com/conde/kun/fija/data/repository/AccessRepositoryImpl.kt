package com.conde.kun.fija.data.repository

import com.conde.kun.fija.data.datasource.AccessDataSource
import com.conde.kun.fija.data.datasource.SettingsDataSource
import com.conde.kun.fija.data.exception.InvalidCredentialsException
import com.conde.kun.fija.data.mapper.UserMapper
import com.conde.kun.fija.domain.model.User
import com.conde.kun.fija.domain.repository.AccessRepository

class AccessRepositoryImpl(
    private val accessDataSource: AccessDataSource,
    private val userMapper: UserMapper,
    private val settingsDataSource: SettingsDataSource
) : AccessRepository {

    override suspend fun getToken(): String? {
        return settingsDataSource.getToken()
    }

    override suspend fun clearToken() {
        settingsDataSource.setToken(null)
    }

    override suspend fun login(useraccount: String, password: String): User {
        val result = this.accessDataSource.login(useraccount, password)

        if (result.errorCode != null && result.errorCode!!.compareTo(4) == 0) {
            throw InvalidCredentialsException()
        }

        settingsDataSource.setToken(result.token)
        return userMapper.map(result.user!!)
    }

    override suspend fun register(
        useraccount: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        accessDataSource.register(useraccount, password, firstName, lastName)
    }

    override suspend fun getUserInfo(): User {
        return userMapper.map(accessDataSource.getUserInfo())
    }

}