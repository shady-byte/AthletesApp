package com.fintold.athletesapp.dataSource.remoteSource

import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.dataSource.dataClasses.RemoteSource

class RemoteDataSource(): RemoteSource {
    override suspend fun getObjects(): Result<List<Athlete>> {
       return try {
           val result = AthletesApi.athletesApiService.getObjects()
           Result.Success(result.athletes)
       } catch (ex: Exception) {
           Result.Error("Error while fetching data from Api ${ex.message}")
       }
    }
}