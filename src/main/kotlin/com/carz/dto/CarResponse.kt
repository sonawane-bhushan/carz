package com.carz.dto

import com.carz.model.Car
import com.carz.model.FuelType

class CarResponse(
    val id: String,
    var name: String,
    var brand: String,
    var images: List<String>,
    var fuelType: FuelType,
    var ncapRating: Float,
    var description: String,
    var rating: Float
) {
    companion object {
        fun fromEntity(car: Car): CarResponse {
            return CarResponse(
                id = car.id!!,
                name = car.name,
                brand = car.brand,
                images = car.images,
                fuelType = car.fuelType,
                ncapRating = car.ncapRating,
                description = car.description,
                rating = car.rating
            )
        }
    }
}