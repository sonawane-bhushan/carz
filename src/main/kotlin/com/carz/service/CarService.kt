package com.carz.service

import com.carz.dto.CarResponse
import com.carz.model.FuelType
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CarService() {
    fun getAll(): Mono<List<CarResponse>> {
        TODO("Not yet implemented")
    }

    fun findById(id: String): Mono<CarResponse> {
        TODO("Not yet implemented")
    }

    fun findByName(name: String): Mono<List<CarResponse>> {
        TODO("Not yet implemented")
    }

    fun findByFuelType(fuelType: FuelType): Mono<List<CarResponse>> {
        TODO("Not yet implemented")
    }

    fun findByRatings(minValue: Double, maxValue: Double): Mono<List<CarResponse>> {
        TODO("Not yet implemented")
    }

    fun findByBrand(brand: String): Mono<List<CarResponse>> {
        TODO("Not yet implemented")
    }

    fun findByNcapRating(minValue: Double, maxValue: Double): Mono<List<CarResponse>> {
        TODO("Not yet implemented")
    }
}