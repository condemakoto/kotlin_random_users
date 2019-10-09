package com.conde.kun.fija.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.fija.domain.repository.AccessRepository
import kotlinx.coroutines.CoroutineScope

class RegisterUseCase(coroutineScope: CoroutineScope, val accessRepository: AccessRepository) :
    BaseUseCase<Boolean, RegisterUseCase.Params>(coroutineScope) {

    override suspend fun getData(param: Params): Boolean {
        accessRepository.register(
            param.email,
            param.password,
            param.firstName,
            param.lastName
        )
        return true
    }

    data class Params(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String
    )
}