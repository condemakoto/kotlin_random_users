package com.conde.kun.randomusers.data.repository

import com.conde.kun.randomusers.data.datasource.UserDataSource
import com.conde.kun.randomusers.data.mapper.UserMapper
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.domain.repository.UserRepository
import kotlinx.coroutines.delay

class UserRepository(val dataSource : UserDataSource) : UserRepository {

    private val userMapper = UserMapper()

    override suspend fun getUsers(page: Int): ArrayList<User> {
        val result = dataSource.getUsers(page)
        return userMapper.mapAll(result.results)
    }

    override suspend fun storeUserSelectionCount(user: User) {
        delay(2000)
    }
}