package com.fintold.athletesapp.dataSource.localSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fintold.athletesapp.dataSource.dataClasses.Athlete

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addObjects(vararg athletes: Athlete): List<Long>

    @Query("SELECT * FROM athletes_table")
    suspend fun getObjects(): List<Athlete>

    @Query("DELETE FROM athletes_table")
    suspend fun deleteObjects()
}