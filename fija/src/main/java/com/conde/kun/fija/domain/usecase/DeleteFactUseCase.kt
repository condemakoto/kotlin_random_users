package com.conde.kun.fija.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.fija.domain.model.Fact
import com.conde.kun.fija.domain.repository.FactsRepository
import kotlinx.coroutines.CoroutineScope

class DeleteFactUseCase(coroutineScope: CoroutineScope, val repository: FactsRepository)
    : BaseUseCase<Boolean, Fact>(coroutineScope) {

    override suspend fun getData(fact: Fact): Boolean {
        repository.deleteFact(fact)
        return true
    }
}