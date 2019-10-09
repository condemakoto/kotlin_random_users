package com.conde.kun.fija.data.datasource

import com.conde.kun.fija.data.entity.FactEntity

interface FactsDataSource {

    suspend fun getFacts(): List<FactEntity>

}