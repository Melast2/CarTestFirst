package com.example.cartestfirst.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cartestfirst.R
import com.example.cartestfirst.databinding.ActivityCarImageBinding
import com.squareup.picasso.Picasso

class CarImageActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CAR_IMAGE_URI = "com.example.cartestfirst.EXTRA_CAR_IMAGE_URI"
    }

    private lateinit var binding: ActivityCarImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri = intent.getStringExtra(EXTRA_CAR_IMAGE_URI)
        Picasso.get().load(imageUri).into(binding.fullScreenImageView)
    }
}