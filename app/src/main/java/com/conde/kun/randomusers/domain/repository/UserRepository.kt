package com.conde.kun.randomusers.domain.repository

import com.conde.kun.randomusers.domain.model.User

interface UserRepository {
    suspend fun getUsers(page: Int): ArrayList<User>
    suspend fun storeUserSelectionCount(user: User)
}