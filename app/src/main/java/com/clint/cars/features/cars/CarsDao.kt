package com.clint.cars.features.cars

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarsDao {

    @Query("SELECT * FROM cars")
    fun getCars(): List<CarEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(carEntity: CarEntity)
}