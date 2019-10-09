package com.conde.kun.fija.data.datasource.db

import androidx.room.Dao
import androidx.room.Query
import com.conde.kun.fija.domain.model.FactsByUser

@Dao
interface UserFactsDao {
    @Query("select * from factuser")
    fun getUserFacts(): List<FactsByUser>
}