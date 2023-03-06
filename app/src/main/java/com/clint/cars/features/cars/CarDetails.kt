package com.clint.cars.features.cars

import com.google.gson.annotations.SerializedName

data class CarDetails(
    var id: Int,
    var title: String,
    var dateTime: String,
    var tags: ArrayList<String>?,
    @SerializedName("content")
    var CarExtraDetails: ArrayList<CarExtraDetails>?,
    var ingress: String,
    var image: String,
    var created: Int?,
    var changed: Int?
) {
    fun toCarEntity() =
        CarEntity(id = id, title = title, subtitle = ingress, date = dateTime, imageUrl = image)
}