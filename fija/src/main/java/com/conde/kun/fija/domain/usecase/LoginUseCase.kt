package com.conde.kun.fija.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.fija.domain.model.User
import com.conde.kun.fija.domain.repository.AccessRepository
import kotlinx.coroutines.CoroutineScope

class LoginUseCase(coroutineScope: CoroutineScope, private val repository: AccessRepository) :
    BaseUseCase<User, LoginUseCase.LoginParams>(coroutineScope) {

    override suspend fun getData(param: LoginParams): User {
        return repository.login(param.username, param.password)
    }

    data class LoginParams(val username: String, val password: String)
}

