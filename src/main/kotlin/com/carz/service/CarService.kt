package com.carz.service

import com.carz.model.Car
import com.carz.model.FuelType
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CarService {
    fun getAll(): Flux<Car>
    fun findById(id: String): Mono<Car>
    fun findByName(name: String): Flux<Car>
    fun findByFuelType(fuelType: FuelType): Flux<Car>
    fun findByRatings(minValue: Double, maxValue: Double): Flux<Car>
    fun findByBrand(brand: String): Flux<Car>
    fun findByNcapRating(minValue: Double, maxValue: Double): Flux<Car>
}