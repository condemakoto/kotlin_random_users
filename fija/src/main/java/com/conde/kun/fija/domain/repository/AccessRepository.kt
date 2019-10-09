package com.conde.kun.fija.domain.repository

import com.conde.kun.fija.domain.model.User

interface AccessRepository {

    suspend fun getToken(): String?

    suspend fun clearToken()

    suspend fun login(useraccount: String, password: String): User

    suspend fun register(useracount: String, password: String, firstName: String, lastName: String)

    suspend fun getUserInfo(): User
}