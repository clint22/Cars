package com.clint.cars.features.cars

import retrofit2.Call
import retrofit2.http.GET

internal interface CarsApi {
    @GET("90ea9402f220b20e423f")
    fun getCars(): Call<Cars>
}