package com.clint.cars.features.cars

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarEntity::class], version = 1)
abstract class CarsDatabase : RoomDatabase() {
    abstract fun carsDao(): CarsDao
}