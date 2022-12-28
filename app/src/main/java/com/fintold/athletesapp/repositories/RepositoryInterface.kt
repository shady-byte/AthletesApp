package com.fintold.athletesapp.repositories

import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.dataClasses.Athlete

interface RepositoryInterface {
    suspend fun getObjects(): Result<List<Athlete>>
    suspend fun deleteObjects(): Result<Boolean>
}