package com.conde.kun.fija.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.fija.domain.model.FactsByUser
import com.conde.kun.fija.domain.repository.FactsRepository
import kotlinx.coroutines.CoroutineScope

class GetUserFactsUseCase(coroutineScope: CoroutineScope, val repository: FactsRepository) :
    BaseUseCase<List<FactsByUser>, Any?>(coroutineScope) {

    override suspend fun getData(param: Any?): List<FactsByUser> {
        return repository.getUserFacs()
    }
}