package com.conde.kun.fija.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.fija.domain.model.User
import com.conde.kun.fija.domain.repository.AccessRepository
import kotlinx.coroutines.CoroutineScope

class GetUserInfoUseCase(coroutineScope: CoroutineScope, val accessRepository: AccessRepository) :
    BaseUseCase<User, Any?>(coroutineScope) {

    override suspend fun getData(param: Any?): User {
        return accessRepository.getUserInfo()
    }

}