package com.carz.service

import com.carz.exception.CarNotFoundException
import com.carz.model.Car
import com.carz.model.FuelType
import com.carz.repository.CarRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class CarServiceTest {

    private val carRepository = mockk<CarRepository>()
    private lateinit var carService: CarServiceImpl

    @BeforeEach
    fun init() {
        carService = CarServiceImpl(carRepository)
    }

    private val carResult = listOf(
        Car(
            id = "1111",
            name = "Nexon",
            brand = "Tata",
            images = listOf(""),
            fuelType = FuelType.ELECTRIC,
            ncapRating = 4.1f,
            description = "",
            rating = 4.1f
        ),
        Car(
            id = "1112",
            name = "i20",
            brand = "Hyndai",
            images = listOf(""),
            fuelType = FuelType.PETROL,
            ncapRating = 3.5f,
            description = "",
            rating = 4.2f
        ),
        Car(
            id = "1113",
            name = "XUV",
            brand = "Mahindra",
            images = listOf(""),
            fuelType = FuelType.DIESEL,
            ncapRating = 4.1f,
            description = "",
            rating = 4.5f
        )
    )

    @Test
    fun `should get list of all cars`() {
        every { carRepository.findAll() }.returns(Flux.fromIterable(carResult))

        val actualResponse = carService.getAll()

        StepVerifier.create(actualResponse)
            .expectNextSequence(carResult)
            .verifyComplete()
    }

    @Test
    fun `should find car by id`() {
        val id = "1111"
        every { carRepository.findById(id) }.returns(Mono.just(carResult.get(1)))

        val actualResponse = carService.findById(id)

        StepVerifier.create(actualResponse)
            .expectNext(carResult.get(1))
            .verifyComplete()
    }

    @Test
    fun `should throw excpetion when car with id not found`() {
        val id = "2222"
        every { carRepository.findById(id) }.returns(Mono.empty())

        val actualResponse = carService.findById(id)

        StepVerifier.create(actualResponse)
            .consumeErrorWith{
                it is CarNotFoundException
            }.verify()
    }

    @Test
    fun `should find cars by name`(){
        val name = "Nexon"
        val expectedResponse = carResult.find{it.name == name}
        every { carRepository.findCarByName(name) }.returns(Flux.just(expectedResponse))

        val actualResponse = carService.findByName(name)

        StepVerifier.create(actualResponse)
            .expectNext(expectedResponse)
            .verifyComplete()
    }

    @Test
    fun `should find cars by fuel type`(){
        val fuelType = FuelType.ELECTRIC
        val expectedResponse = carResult.find{it.fuelType == FuelType.ELECTRIC}
        every { carRepository.findCarByFuelType(fuelType) }.returns(Flux.just(expectedResponse))

        val actualResponse = carService.findByFuelType(fuelType)

        StepVerifier.create(actualResponse)
            .expectNext(expectedResponse)
            .verifyComplete()
    }

    @Test
    fun `should find cars by ratings range`(){
        val minValue = 4.1
        val maxValue = 4.3
        val expectedResponse = carResult.find { it.rating < maxValue && it.rating > minValue }

        val actualResponse = carService.findByRatings(minValue, maxValue)

        StepVerifier.create(actualResponse)
            .expectNext(expectedResponse)
            .verifyComplete()
    }

}