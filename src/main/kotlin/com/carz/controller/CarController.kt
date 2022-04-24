package com.carz.controller

import com.carz.dto.CarResponse
import com.carz.model.FuelType
import com.carz.service.CarServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/car")
class CarController(private val carService: CarServiceImpl) {

    @GetMapping("/all")
    fun getAllCars(): Flux<CarResponse> {
        return carService.getAll().map { CarResponse.fromEntity(it) }
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: String): Mono<CarResponse>{
        return carService.findById(id).map { CarResponse.fromEntity(it) }
    }

    @GetMapping("/name/{name}")
    fun findByName(@PathVariable name: String): Flux<CarResponse> {
        return carService.findByName(name).map { CarResponse.fromEntity(it) }
    }

    @GetMapping("/fuel/{fuelType}")
    fun findByFuelType(@PathVariable fuelType: FuelType): Flux<CarResponse> {
        return carService.findByFuelType(fuelType).map { CarResponse.fromEntity(it) }
    }

    @GetMapping("/rating/{min}/{max}")
    fun findByRating(@PathVariable min: Double, @PathVariable max: Double): Flux<CarResponse> {
        return carService.findByRatings(min, max).map { CarResponse.fromEntity(it) }
    }

    @GetMapping("/ncap-rating/{min}/{max}")
    fun findByNcapRating(@PathVariable min: Double, @PathVariable max: Double): Flux<CarResponse> {
        return carService.findByNcapRating(min, max).map { CarResponse.fromEntity(it) }
    }

    @GetMapping("/brand/{brand}")
    fun findByBrand(@PathVariable brand: String): Flux<CarResponse> {
        return carService.findByBrand(brand).map { CarResponse.fromEntity(it) }
    }
}