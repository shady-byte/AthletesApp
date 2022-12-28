package com.fintold.athletesapp.dataSource.localSource

import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.dataSource.dataClasses.LocalSource
import com.fintold.athletesapp.adapters.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val databaseDao: DatabaseDao): LocalSource {
    override suspend fun addObjects(athletes: List<Athlete>): Result<Boolean> {
        return try {
            val result = databaseDao.addObjects(*athletes.toTypedArray())
            if(result.isEmpty()) {
                Result.Error("Error while adding objects to database")
            } else {
                Result.Success(true)
            }
        } catch (ex: Exception) {
            Result.Error("Error while adding objects to database ${ex.message}")
        }
    }

    override suspend fun getObjects(): Result<List<Athlete>> {
        return try {
            val result = databaseDao.getObjects()
            if(result.isEmpty()) {
                Result.Error("Error while getting objects to database")
            } else {
                Result.Success(result)
            }
        } catch (ex: Exception) {
            Result.Error("Error while getting objects to database ${ex.message}")
        }
    }

    override suspend fun deleteObjects(): Result<Boolean> {
        return try {
            databaseDao.deleteObjects()
            Result.Success(true)
        }catch (ex: Exception) {
            Result.Error("Error while deleting objects from database ${ex.message}")
        }
    }
}
