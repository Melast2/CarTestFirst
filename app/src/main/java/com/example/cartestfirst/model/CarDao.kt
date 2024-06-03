package com.example.cartestfirst.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAllCars(): LiveData<List<Car>>

    @Query("SELECT * FROM cars WHERE id = :carId")
    fun getCarById(carId: Int): LiveData<Car>

    @Query("SELECT * FROM cars WHERE brand =:brand")
    fun getCarsByBrand(brand:String): LiveData<Car>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car)

    @Update
    suspend fun update(car: Car)

    suspend fun delete(car: Car)
}