package com.conde.kun.randomusers.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope

class StoreUserSelectionCountUseCase(
    coroutineScope: CoroutineScope,
    val userRepository: UserRepository
) :
    BaseUseCase<Unit, User>(coroutineScope) {

    override suspend fun getData(user: User) {
        userRepository.storeUserSelectionCount(user)
    }

}