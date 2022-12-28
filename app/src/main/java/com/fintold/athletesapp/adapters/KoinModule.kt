package com.fintold.athletesapp.adapters

import com.fintold.athletesapp.dataSource.dataClasses.LocalSource
import com.fintold.athletesapp.dataSource.dataClasses.RemoteSource
import com.fintold.athletesapp.dataSource.localSource.AthletesDatabase
import com.fintold.athletesapp.dataSource.localSource.LocalDataSource
import com.fintold.athletesapp.dataSource.remoteSource.RemoteDataSource
import com.fintold.athletesapp.repositories.AthletesRepository
import com.fintold.athletesapp.repositories.RepositoryInterface
import com.fintold.athletesapp.uI.AthletesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AthletesDatabase.getInstance(get()).databaseDao }
    single<LocalSource> { LocalDataSource(get()) }
    single<RemoteSource> { RemoteDataSource() }
    single<RepositoryInterface> { AthletesRepository(get(),get()) }
    viewModel { AthletesViewModel(get()) }
}
