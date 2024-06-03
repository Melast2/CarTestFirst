package com.example.cartestfirst.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cartestfirst.adapter.CarListAdapter
import com.example.cartestfirst.databinding.ActivityMainBinding
import com.example.cartestfirst.model.Car
import com.example.cartestfirst.viewmodel.CarViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var carViewModel: CarViewModel
    private val carListAdapter = CarListAdapter(
        onCarClicked = { cars -> showCarImage(cars) },
        onEditClicked = { cars -> editCar(cars) },
        onDeleteClicked = { cars -> deleteCar(cars) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        carViewModel = ViewModelProvider(this)[CarViewModel::class.java]
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = carListAdapter
        }

        carViewModel.allCars.observe(this) { cars ->
            cars?.let { carListAdapter.setCars(it) }
        }

        binding.addCarButton.setOnClickListener {
            addCar()
        }
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
//                return false
//            }
//        })

        binding.filterEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
              filterCars(s.toString())
            }
        })

    }

    private fun addCar() {
        val intent = Intent(this, AddEditCarActivity::class.java)
        startActivity(intent)
    }


    private fun editCar(car: Car) {
        val intent = Intent(this, AddEditCarActivity::class.java).apply {
            putExtra(AddEditCarActivity.CAR_ID, car.id)
        }
        startActivity(intent)
    }

    private fun deleteCar(car: Car) {
        carViewModel.delete(car)
    }

    private fun filterCars(filter: String) {
//        carViewModel.getCarById("%$filter%").observe(this) { cars ->
//            cars?.let {
//                carListAdapter.setCars(it)
//            }
//        }
    }

    private fun showCarImage(car: Car) {
        val intent = Intent(this, CarImageActivity::class.java).apply {
            putExtra(CarImageActivity.EXTRA_CAR_IMAGE_URI, car.imageUri)
        }
        startActivity(intent)
    }
}