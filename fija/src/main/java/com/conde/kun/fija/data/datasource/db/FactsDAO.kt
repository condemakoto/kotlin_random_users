package com.conde.kun.fija.data.datasource.db

import androidx.room.*
import com.conde.kun.fija.domain.model.Fact

@Dao
interface FactsDAO {

    @Query("SELECT * from fact")
    fun getAll(): List<Fact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fact: Fact)

    @Delete
    fun delete(fact: Fact)

}