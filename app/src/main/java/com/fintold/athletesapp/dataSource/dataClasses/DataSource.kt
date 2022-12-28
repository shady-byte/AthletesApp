package com.fintold.athletesapp.dataSource.dataClasses

import com.fintold.athletesapp.adapters.Result

interface DataSource {
    suspend fun getObjects(): Result<List<Athlete>>
}

interface LocalSource: DataSource {
    suspend fun addObjects(athletes: List<Athlete>): Result<Boolean>
    suspend fun deleteObjects(): Result<Boolean>
}
interface RemoteSource: DataSource {}

