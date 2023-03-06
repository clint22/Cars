package com.clint.cars.features.cars

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsService
@Inject constructor(private val retrofit: Retrofit) : CarsApi {
    private val carsApi by lazy { retrofit.create(CarsApi::class.java) }

    override fun getCars(): Call<Cars> =
        carsApi.getCars()

}