package com.conde.kun.fija.domain.usecase

import com.conde.kun.core.domain.BaseUseCase
import com.conde.kun.fija.domain.model.Fact
import com.conde.kun.fija.domain.repository.FactsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

class GetFactsUseCase(coroutineScope: CoroutineScope, val factsRepository: FactsRepository) :
    BaseUseCase<List<Fact>, Any?>(coroutineScope) {

    override suspend fun getData(param: Any?): List<Fact> {

        val facts = factsRepository.getFacts()
        postProgress(20)
        delay(2000)

        factsRepository.getFavoritedFacts().forEach { fact ->
            facts.forEach {
                if (fact.id == it.id) {
                    it.favorited = true
                }
            }
        }
        postProgress(90)
        delay(2000)
        return facts
    }

}