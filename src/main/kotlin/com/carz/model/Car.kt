package com.carz.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Car (
    @Id
    val id: String ?= null,
    var name: String,
    var brand: String,
    var images: List<String>,
    var fuelType: FuelType,
    var ncapRating: Float,
    var description: String,
    var rating: Float
)

enum class FuelType {
    PETROL,
    DIESEL,
    CNG,
    ELECTRIC
}
