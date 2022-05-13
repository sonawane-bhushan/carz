package com.carz.dto

import com.carz.model.Car
import com.carz.model.FuelType

class CarRequest(
    var name: String,
    var brand: String,
    var images: List<String>,
    var fuelType: FuelType,
    var ncapRating: Float,
    var description: String,
    var rating: Float
)
