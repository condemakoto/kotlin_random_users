package com.conde.kun.fija.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.fija.domain.repository.AccessRepository
import kotlinx.coroutines.CoroutineScope

class LogoutUseCase(coroutineScope: CoroutineScope, val accessRepository: AccessRepository) :
    BaseUseCase<Boolean, Any?>(coroutineScope) {

    override suspend fun getData(param: Any?): Boolean {
        accessRepository.clearToken()
        return true
    }
}