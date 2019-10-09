package com.conde.kun.fija.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.conde.kun.fija.domain.model.Fact
import com.conde.kun.fija.domain.model.FactUser


@Database(entities = arrayOf(Fact::class, FactUser::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun factDao(): FactsDAO
    abstract fun userDao(): UserDao
    abstract fun userFactsDao(): UserFactsDao
}