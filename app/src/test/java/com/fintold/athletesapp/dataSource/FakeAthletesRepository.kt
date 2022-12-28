package com.fintold.athletesapp.dataSource

import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.repositories.RepositoryInterface

class FakeAthletesRepository(private val data: MutableList<Athlete>? = mutableListOf()): RepositoryInterface {

    override suspend fun getObjects(): Result<List<Athlete>> {
        return Result.Success(data!!)
    }

    override suspend fun deleteObjects(): Result<Boolean> {
        data?.clear()
        return if(data?.isEmpty()!!)
            Result.Success(true)
        else
            Result.Error("Error while deleting data")
    }
}