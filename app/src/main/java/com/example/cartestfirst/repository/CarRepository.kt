package com.example.cartestfirst.repository

import androidx.lifecycle.LiveData
import com.example.cartestfirst.model.Car
import com.example.cartestfirst.model.CarDao

class CarRepository(private val carDao: CarDao) {

    val allCars: LiveData<List<Car>> = carDao.getAllCars()

    suspend fun insert(car: Car) {
        carDao.insert(car)
    }

    suspend fun update(car: Car) {
        carDao.update(car)
    }

    suspend fun delete(car: Car) {
        carDao.delete(car)
    }

    fun getCarsByBrand(filter: String): LiveData<Car> {
        return carDao.getCarsByBrand(brand = filter)
    }

}
