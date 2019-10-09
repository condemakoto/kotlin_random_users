package com.conde.kun.fija.data.repository

import com.conde.kun.fija.data.datasource.FactsDataSource
import com.conde.kun.fija.data.datasource.db.FactsDAO
import com.conde.kun.fija.data.datasource.db.UserDao
import com.conde.kun.fija.data.datasource.db.UserFactsDao
import com.conde.kun.fija.data.mapper.FactMapper
import com.conde.kun.fija.data.mapper.FactUserMapper
import com.conde.kun.fija.domain.model.Fact
import com.conde.kun.fija.domain.model.FactsByUser
import com.conde.kun.fija.domain.repository.FactsRepository

class FactsRepositoryImpl(
    val factsDataSource: FactsDataSource,
    val factMapper: FactMapper,
    val factsDao: FactsDAO,
    val userDao: UserDao,
    val userFactsDao: UserFactsDao
) :
    FactsRepository {


    override suspend fun getFacts(): List<Fact> {
        return factMapper.mapAll(factsDataSource.getFacts())
    }

    override suspend fun getFavoritedFacts(): List<Fact> {
        return factsDao.getAll()
    }

    override suspend fun getUserFacs(): List<FactsByUser> {
        return userFactsDao.getUserFacts()
    }

    override suspend fun saveFact(fact: Fact) {
        fact.user?.let { userDao.insert(it) }
        factsDao.insert(fact)
    }

    override suspend fun deleteFact(fact: Fact) {
        factsDao.delete(fact)
    }

}