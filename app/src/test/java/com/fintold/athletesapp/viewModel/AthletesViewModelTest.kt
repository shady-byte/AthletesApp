package com.fintold.athletesapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fintold.athletesapp.MainCoroutineRule
import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.FakeAthletesRepository
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.getOrAwaitValue
import com.fintold.athletesapp.uI.AthletesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AthletesViewModelTest {
    private val athlete1 = Athlete("Mochael Jordan","image1","Basketball player")
    private val athlete2 = Athlete("Lionel Messi","image2","Football player")
    private val athlete3 = Athlete("Cristiano Ronaldo","image3","Football player")
    private val localData = mutableListOf<Athlete>(athlete1,athlete2,athlete3)


    private lateinit var repository: FakeAthletesRepository
    private lateinit var viewModel: AthletesViewModel

    //to test function returns live data
    @ExperimentalCoroutinesApi
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    //to test function which runs viewModelSCope
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createViewModel() {
        repository = FakeAthletesRepository(localData)
        viewModel = AthletesViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAthletes_getObjectsFromViewModel_updateLivedata() = runTest {
        viewModel.getAthletes()
        val athletes = viewModel.athletesList
        advanceUntilIdle()
        assertEquals(localData,athletes.getOrAwaitValue())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun deleteAthletes_deleteObjectsFromViewModel_updateLivedata() = runTest {
        viewModel.deleteAthletes()
        val result = repository.getObjects()
        advanceUntilIdle()
        assertEquals(listOf<Athlete>(),(result as Result.Success).data)
    }
}