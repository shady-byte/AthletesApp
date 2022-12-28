package com.fintold.athletesapp.dataSource.remoteSource

import com.fintold.athletesapp.BuildConfig
import com.fintold.athletesapp.dataSource.dataClasses.ResultOfAthletesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val athletesRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BuildConfig.API_URL)
    .build()

interface AthletesApiService {
    @GET(".")
    suspend fun getObjects(): ResultOfAthletesApi
}

object AthletesApi {
    val athletesApiService: AthletesApiService by lazy {
        athletesRetrofit.create(AthletesApiService::class.java)
    }
}