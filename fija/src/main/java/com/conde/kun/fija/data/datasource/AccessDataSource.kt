package com.conde.kun.fija.data.datasource

import com.conde.kun.fija.data.entity.LoginEntity
import com.conde.kun.fija.data.entity.UserInformationEntity

interface AccessDataSource {
    suspend fun login(useraccount: String, password: String): LoginEntity
    suspend fun register(useracount: String, password: String, firstName: String, lastName: String)
    suspend fun getUserInfo(): UserInformationEntity
}