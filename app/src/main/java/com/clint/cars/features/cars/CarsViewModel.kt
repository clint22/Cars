package com.clint.cars.features.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clint.cars.core.interactor.UseCase
import com.clint.cars.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarsViewModel
@Inject constructor(private val getCars: GetCars) : BaseViewModel() {

    private val _cars: MutableLiveData<List<CarEntity>> = MutableLiveData()
    val cars: LiveData<List<CarEntity>> = _cars

    private fun handleCars(carEntities: List<CarEntity>) {
        _cars.value = carEntities
    }

    fun loadCars() = getCars(UseCase.None(), viewModelScope) {
        it.fold(::handleFailure, ::handleCars)
    }
}