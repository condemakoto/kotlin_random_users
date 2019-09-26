package com.conde.kun.randomusers.data.datasource

import com.conde.kun.randomusers.data.entity.UserPageEntity

interface UserDataSource {
    suspend fun getUsers(page: Int): UserPageEntity
}