package com.conde.kun.randomusers.data.repository

import com.conde.kun.randomusers.data.datasource.UserDataSource
import com.conde.kun.randomusers.data.datasource.remote.RemoteUserDataSource
import com.conde.kun.randomusers.data.mapper.UserMapper
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.domain.repository.UserRepository

class UserRepository : UserRepository {

    private val userMapper = UserMapper()
    private val dataSource: UserDataSource = RemoteUserDataSource()

    override suspend fun getUsers(page: Int): ArrayList<User> {
        val result = dataSource.getUsers(page)
        return userMapper.mapAll(result.results)
    }
}