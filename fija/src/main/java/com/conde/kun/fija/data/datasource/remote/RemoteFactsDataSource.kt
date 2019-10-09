package com.conde.kun.fija.data.datasource.remote

import com.conde.kun.fija.data.datasource.FactsDataSource
import com.conde.kun.fija.data.entity.FactApi
import com.conde.kun.fija.data.entity.FactEntity

class RemoteFactsDataSource(val factsService: FactApi) : FactsDataSource {

    override suspend fun getFacts(): List<FactEntity> {
        return factsService.getFacts().all
    }

}