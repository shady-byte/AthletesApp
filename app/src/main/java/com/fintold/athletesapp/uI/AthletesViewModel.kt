package com.fintold.athletesapp.uI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintold.athletesapp.adapters.Result
import com.fintold.athletesapp.dataSource.dataClasses.Athlete
import com.fintold.athletesapp.repositories.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AthletesViewModel(private val repository: RepositoryInterface): ViewModel() {
    private val _athletesList = MutableLiveData<List<Athlete>>()
    val athletesList: LiveData<List<Athlete>>
        get() = _athletesList

    private val _showAlert = MutableLiveData<Boolean>(false)
    val showAlert: LiveData<Boolean>
        get() = _showAlert

    fun getAthletes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getObjects()
            }
            when(result) {
                is Result.Success<List<Athlete>> -> {
                    _athletesList.value = result.data
                    _showAlert.value = false
                }
                else -> {
                    _showAlert.value = true
                }
            }
        }
    }

    fun deleteAthletes() {
        viewModelScope.launch {
            repository.deleteObjects()
        }
    }
}

const val TAG = "MainTest"