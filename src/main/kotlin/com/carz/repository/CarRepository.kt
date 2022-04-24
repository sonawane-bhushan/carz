package com.carz.repository

import com.carz.model.Car
import com.carz.model.FuelType
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface CarRepository: ReactiveMongoRepository<Car, String> {

    fun findCarByName(name: String): Flux<Car>

    fun findCarByFuelType(fuelType: FuelType): Flux<Car>

}
