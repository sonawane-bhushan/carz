package com.carz.service

import com.carz.dto.CarRequest
import com.carz.exception.CarNotFoundException
import com.carz.model.Car
import com.carz.model.FuelType
import com.carz.repository.CarRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CarServiceImpl(private val carRepository: CarRepository) : CarService {

    override fun getAll(): Flux<Car> {
        return carRepository.findAll()
    }

    override fun findById(id: String): Mono<Car> {
        return carRepository.findById(id).switchIfEmpty(
            Mono.error(CarNotFoundException("Car with ID: $id not found"))
        )
    }

    override fun findByName(name: String): Flux<Car> {
        return carRepository.findCarByName(name)
    }

    override fun findByFuelType(fuelType: FuelType): Flux<Car> {
        return carRepository.findCarByFuelType(fuelType)
    }

    override fun add(request: CarRequest): Mono<Car> {
        return carRepository.save(
            Car(
                name = request.name,
                brand = request.brand,
                description = request.description,
                fuelType = request.fuelType,
                images = request.images,
                ncapRating = request.ncapRating,
                rating = request.rating
            )
        )
    }

    override fun findByRatings(minValue: Double, maxValue: Double): Flux<Car> {
        TODO("Not yet implemented")
    }

    override fun findByBrand(brand: String): Flux<Car> {
        TODO("Not yet implemented")
    }

    override fun findByNcapRating(minValue: Double, maxValue: Double): Flux<Car> {
        TODO("Not yet implemented")
    }
}