package com.clint.cars.features.cars

import com.clint.cars.core.exception.Failure
import com.clint.cars.core.functional.Either
import com.clint.cars.core.interactor.UseCase
import javax.inject.Inject


class GetCars @Inject constructor(private val carsRepository: CarsRepository) :
    UseCase<List<CarEntity>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<CarEntity>> = carsRepository.cars()
}