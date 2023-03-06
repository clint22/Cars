package com.clint.cars.features.cars

import com.clint.cars.core.exception.Failure
import com.clint.cars.core.functional.Either
import com.clint.cars.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface CarsRepository {
    fun cars(): Either<Failure, List<CarEntity>>
}

class NetWork @Inject constructor(
    private val carsService: CarsService,
    private val networkHandler: NetworkHandler,
    private val carsDao: CarsDao
) : CarsRepository {

    override fun cars(): Either<Failure, List<CarEntity>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> {
                request(
                    carsService.getCars(),
                    {
                        it.carDetails?.forEach { carDetails ->
                            carsDao.insertCar(carDetails.toCarEntity())
                        }
                        carsDao.getCars()
                    },
                    Cars.empty
                )
            }
            false -> {
                if (carsDao.getCars().isNotEmpty()) {
                    Either.Right(carsDao.getCars())
                } else {
                    Either.Left(Failure.NetworkConnection)
                }
            }

        }
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    Either.Right(transform((response.body() ?: default)))
                }
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }

}