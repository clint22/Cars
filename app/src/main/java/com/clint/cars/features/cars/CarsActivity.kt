package com.clint.cars.features.cars

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.clint.cars.core.exception.Failure
import com.clint.cars.core.extensions.failure
import com.clint.cars.core.extensions.observe
import com.clint.cars.databinding.ActivityCarsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarsActivity : AppCompatActivity() {
    @Inject
    lateinit var carsAdapter: CarsAdapter
    private lateinit var binding: ActivityCarsBinding
    private val carsViewModel: CarsViewModel by viewModels()
    var keepSplashOnScreen = true
    val splashScreenDelay = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        super.onCreate(savedInstanceState)
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialiseView()
        loadCarList()
        with(carsViewModel) {
            observe(cars, ::renderCars)
            failure(failure, ::handleFailure)
        }
        Handler(Looper.getMainLooper()).postDelayed(
            { keepSplashOnScreen = false },
            splashScreenDelay
        )
    }

    private fun initialiseView() {
        binding.recyclerViewCars.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCars.adapter = carsAdapter
    }

    private fun loadCarList() {
        carsViewModel.loadCars()
    }

    private fun handleFailure(failure: Failure?) {
        Log.e("cars_failure", Gson().toJson(failure))
        binding.progressCars.visibility = View.GONE
        binding.lottieError.visibility = View.VISIBLE
        binding.lottieError.playAnimation()
    }

    private fun renderCars(cars: List<CarEntity>?) {
        binding.progressCars.visibility = View.GONE
        binding.recyclerViewCars.visibility = View.VISIBLE
        carsAdapter.carDetailsList = cars.orEmpty()
    }
}