package com.example.cartestfirst.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cartestfirst.R
import com.example.cartestfirst.model.Car
import com.squareup.picasso.Picasso

class CarListAdapter(
    private val onCarClicked: (Car) -> Unit,
    private val onEditClicked: (Car) -> Unit,
    private val onDeleteClicked: (Car) -> Unit
) : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    private var cars: List<Car> = listOf()
    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carImageView: ImageView = itemView.findViewById(R.id.carImageView)
        private val carBrandTextView: TextView = itemView.findViewById(R.id.carBrandTextView)
        private val carModelTextView: TextView = itemView.findViewById(R.id.carModelTextView)
        private val carYearTextView: TextView = itemView.findViewById(R.id.carYearTextView)
        private val editButton: ImageView = itemView.findViewById(R.id.editButton)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

        fun bind(car: Car) {
            carBrandTextView.text = car.brand
            carModelTextView.text = car.model
            carYearTextView.text = car.year.toString()
            Picasso.get().load(car.imageUri).into(carImageView)

            carImageView.setOnClickListener { onCarClicked(car) }
            editButton.setOnClickListener { onEditClicked(car) }
            deleteButton.setOnClickListener { onDeleteClicked(car) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    fun setCars(cars: List<Car>) {
        this.cars = cars
        notifyDataSetChanged()
    }
}