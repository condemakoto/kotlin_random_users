package com.conde.kun.randomusers.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.domain.repository.UserRepository

class GetUserUseCase(val userRepository: UserRepository) :
    BaseUseCase<List<User>, GetUserUseCase.Param>() {

    override suspend fun getData(param: Param): List<User> {
        return userRepository.getUsers(param.page)
    }

    data class Param(val page: Int)
}