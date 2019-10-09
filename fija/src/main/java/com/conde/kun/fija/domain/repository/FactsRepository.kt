package com.conde.kun.fija.domain.repository

import com.conde.kun.fija.domain.model.Fact
import com.conde.kun.fija.domain.model.FactsByUser

interface FactsRepository {
    suspend fun getFacts(): List<Fact>
    suspend fun getFavoritedFacts(): List<Fact>
    suspend fun getUserFacs(): List<FactsByUser>
    suspend fun saveFact(fact: Fact)
    suspend fun deleteFact(fact: Fact)
}