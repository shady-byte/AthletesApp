package com.fintold.athletesapp.repositories

import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.dataSource.dataClasses.LocalSource
import com.fintold.athletesapp.dataSource.dataClasses.RemoteSource

class AthletesRepository(private val localSource: LocalSource, private val remoteSource: RemoteSource): RepositoryInterface {
    override suspend fun getObjects(): Result<List<Athlete>> {
        return try {
            val result = remoteSource.getObjects()
            if(result is Result.Success<List<Athlete>>) {
                localSource.addObjects(result.data)
            }
            when(val response = localSource.getObjects()) {
                is Result.Success<List<Athlete>> -> {
                    Result.Success(response.data)
                }
                else -> {
                    Result.Error("Error while getting athletes from repository")
                }
            }
        } catch (ex: Exception) {
          Result.Error("Error while getting athletes from repository ${ex.message}")
        }

    }

    override suspend fun deleteObjects(): Result<Boolean> {
        return try {
            when(val result = localSource.deleteObjects()) {
                is Result.Success<Boolean> -> {
                    Result.Success(true)
                }
                else -> {
                    Result.Error("Error while getting athletes from repository")
                }
            }
        }catch (ex: Exception) {
            Result.Error("Error while getting athletes from repository ${ex.message}")
        }
    }
}