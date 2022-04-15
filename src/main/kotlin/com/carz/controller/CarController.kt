package com.carz.controller

import com.carz.dto.CarResponse
import com.carz.model.FuelType
import com.carz.service.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/car")
class CarController(private val carService: CarService) {

    @GetMapping("/all")
    fun getAllCars(): Mono<List<CarResponse>> {
        return carService.getAll()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: String): Mono<CarResponse>{
        return carService.findById(id)
    }

    @GetMapping("/name/{name}")
    fun findByName(@PathVariable name: String): Mono<List<CarResponse>> {
        return carService.findByName(name)
    }

    @GetMapping("/fuel/{fuelType}")
    fun findByFuelType(@PathVariable fuelType: FuelType): Mono<List<CarResponse>> {
        return carService.findByFuelType(fuelType)
    }

    @GetMapping("/rating/{min}/{max}")
    fun findByRating(@PathVariable min: Double, @PathVariable max: Double): Mono<List<CarResponse>> {
        return carService.findByRatings(min, max)
    }

    @GetMapping("/ncap-rating/{min}/{max}")
    fun findByNcapRating(@PathVariable min: Double, @PathVariable max: Double): Mono<List<CarResponse>> {
        return carService.findByNcapRating(min, max)
    }

    @GetMapping("/brand/{brand}")
    fun findByBrand(@PathVariable brand: String): Mono<List<CarResponse>> {
        return carService.findByBrand(brand)
    }
}