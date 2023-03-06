package com.clint.cars.features.cars

import com.google.gson.annotations.SerializedName

data class Cars(
    var status: String?,
    @SerializedName("content") var carDetails: List<CarDetails>?,
    var serverTime: Int?
) {
    companion object {
        val empty = Cars(null, null, null)
    }
}