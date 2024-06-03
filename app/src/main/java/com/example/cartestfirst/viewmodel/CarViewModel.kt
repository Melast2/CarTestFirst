package com.example.cartestfirst.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cartestfirst.model.Car
import com.example.cartestfirst.model.CarDatabase
import com.example.cartestfirst.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

     val repository: CarRepository = CarRepository(application)
    val allCars: LiveData<List<Car>>

    init {
        val carDao = CarDatabase.getDatabase(application).carDao()
        repository = CarRepository(carDao)
        allCars = repository.allCars
    }
    fun insert(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(car)
    }

    fun update(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(car)
    }

    fun delete(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(car)
    }

    fun getCarsByBrand(filter: String): LiveData<List<Car>> {
        return repository.getCarsByBrand(filter)
    }
}