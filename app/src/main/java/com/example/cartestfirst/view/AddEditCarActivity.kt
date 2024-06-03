package com.example.cartestfirst.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cartestfirst.R
import com.example.cartestfirst.databinding.ActivityAddEditCarBinding
import com.example.cartestfirst.model.Car
import com.example.cartestfirst.viewmodel.CarViewModel

@Suppress("DEPRECATION")
class AddEditCarActivity : AppCompatActivity() {

    companion object {
        const val CAR_ID = "com.example.cartestfirst"
        const val PICK_IMAGE_REQUEST = 1
    }

    private lateinit var binding: ActivityAddEditCarBinding
    private lateinit var carViewModel: CarViewModel
    private var carId: Int = 0
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        carId = intent.getIntExtra(CAR_ID, 0)

        if (carId != 0) {
            carViewModel.allCars.observe(this) { cars ->
                cars.find { it.id == carId }?.let { car ->
                    binding.carBrandEditText.setText(car.brand)
                    binding.carModelEditText.setText(car.model)
                    binding.carYearEditText.setText(car.year.toString())
                    imageUri = Uri.parse(car.imageUri)
                    binding.carImageView.setImageURI(imageUri)
                }
            }
        }

        binding.selectImageButton.setOnClickListener {
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            }
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        }

        binding.saveCarButton.setOnClickListener {
            saveCar()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            binding.carImageView.setImageURI(imageUri)
        }
    }

    private fun saveCar() {
        val brand = binding.carBrandEditText.text.toString()
        val model = binding.carModelEditText.text.toString()
        val year = binding.carYearEditText.text.toString().toIntOrNull()

        if (brand.isBlank() || model.isBlank() || year == null || imageUri == null) {
            // Ошибки
            return
        }

        val car = Car(
            id = carId,
            brand = brand,
            model = model,
            year = year,
            imageUri = imageUri.toString()
        )

        if (carId == 0) {
            carViewModel.insert(car)
        } else {
            carViewModel.update(car)
        }

        finish()
    }
}