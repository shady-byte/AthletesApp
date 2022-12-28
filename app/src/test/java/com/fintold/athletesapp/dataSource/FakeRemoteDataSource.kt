package com.fintold.athletesapp.dataSource

import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.dataSource.dataClasses.RemoteSource

class FakeRemoteDataSource(private val data: List<Athlete>? = listOf()): RemoteSource {
    override suspend fun getObjects(): Result<List<Athlete>> {
        return Result.Success(data!!)
    }
}