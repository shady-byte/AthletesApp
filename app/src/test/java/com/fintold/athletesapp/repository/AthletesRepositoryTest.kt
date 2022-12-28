package com.fintold.athletesapp.repository

import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.FakeLocalDataSource
import com.fintold.athletesapp.dataSource.FakeRemoteDataSource
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.dataSource.dataClasses.LocalSource
import com.fintold.athletesapp.dataSource.dataClasses.RemoteSource
import com.fintold.athletesapp.repositories.AthletesRepository
import com.fintold.athletesapp.repositories.RepositoryInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AthletesRepositoryTest {
    private val athlete1 = Athlete("Mochael Jordan","image1","Basketball player")
    private val athlete2 = Athlete("Lionel Messi","image2","Football player")
    private val athlete3 = Athlete("Cristiano Ronaldo","image3","Football player")
    private val localAthletes = listOf<Athlete>(athlete1,athlete2,athlete3)

    private lateinit var remoteSource: RemoteSource
    private lateinit var localSource: LocalSource
    private lateinit var athletesRepository: RepositoryInterface

    @Before
    fun createRepository() {
        remoteSource = FakeRemoteDataSource(localAthletes)
        localSource = FakeLocalDataSource()
        athletesRepository = AthletesRepository(localSource = localSource, remoteSource = remoteSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getObjects_requestAthletesFromRepository_returnListOfAthletes() = runBlocking {
        val athletes = athletesRepository.getObjects()
        assertEquals(localAthletes,(athletes as Result.Success).data)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteObjects_deleteLocalAthletes_returnTrue() = runBlocking {
        val result = athletesRepository.deleteObjects()
        assertEquals(true,(result as Result.Success).data)
    }


}