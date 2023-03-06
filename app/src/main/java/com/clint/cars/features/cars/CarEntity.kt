package com.clint.cars.features.cars

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String
)