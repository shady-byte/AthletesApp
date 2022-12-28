package com.fintold.athletesapp.dataSource

import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.dataSource.dataClasses.LocalSource

class FakeLocalDataSource(private val data: MutableList<Athlete>? = mutableListOf()): LocalSource {
    override suspend fun addObjects(athletes: List<Athlete>): Result<Boolean> {
        data?.addAll(athletes)
        return if(data?.containsAll(athletes)!!)
            Result.Success(true)
        else
            Result.Error("Error while adding data")
    }

    override suspend fun deleteObjects(): Result<Boolean> {
        data?.clear()
        return if(data?.isEmpty()!!)
            Result.Success(true)
        else
            Result.Error("Error while deleting data")
    }

    override suspend fun getObjects(): Result<List<Athlete>> {
        return Result.Success(data!!)
    }
}