package com.conde.kun.fija.data.datasource.db

import androidx.room.*
import com.conde.kun.fija.domain.model.FactUser

@Dao
interface UserDao {

    @Query("select *  from factuser")
    fun listUsers(): List<FactUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: FactUser)

    @Delete
    fun delete(user: FactUser)

    @Update
    fun update(user: FactUser)

}