package com.carz.controller

import com.carz.dto.CarResponse
import com.carz.model.Car
import com.carz.model.FuelType
import com.carz.service.CarServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.reactive.server.expectBodyList
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest
internal class CarControllerTest {

    @MockBean
    private lateinit var carService: CarServiceImpl

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `should list all the cars`() {
        `when`(carService.getAll()).thenReturn(Flux.fromIterable(carResult))

        webTestClient.get()
            .uri("/api/car/all")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<CarResponse>()
            .hasSize(3)
    }

    @Test
    fun `should return car by id`() {
        val expectedResponse = carResult[1]
        `when`(carService.findById("1111")).thenReturn(Mono.just(carResult[1]))

        val actualResponse = webTestClient.get()
            .uri("/api/car/1111")
            .exchange()
            .expectStatus().isOk
            .expectBody<CarResponse>()
            .returnResult()
            .responseBody

        assertEqualsCars(convertToCarResponse(expectedResponse), actualResponse!!)
    }

    @Test
    fun `should throw exception when car with id is not present`(){
        `when`(carService.findById("1")).thenReturn(Mono.empty())

        webTestClient.get()
            .uri("/api/car/1")
            .exchange()
            .expectStatus().isNotFound
    }

    @Test
    fun `should find cars by name`(){
        val expectedResponse = carResult.find { it.name == "Nexon" }!!
        `when`(carService.findByName("Nexon")).thenReturn(Flux.just(expectedResponse))

        val actualResponse = webTestClient.get()
            .uri("/api/car/name/Nexon")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<CarResponse>()
            .returnResult()
            .responseBody

        assertEqualsCars(convertToCarResponse(expectedResponse), actualResponse!!.first())
    }

    @Test
    fun `should find cars by fuel type`(){
        val expectedResponse = carResult.find { it.fuelType == FuelType.ELECTRIC }!!
        `when`(carService.findByFuelType(FuelType.ELECTRIC)).thenReturn(Flux.just(expectedResponse))

        val actualResponse = webTestClient.get()
            .uri("/api/car/fuel/ELECTRIC")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<CarResponse>()
            .returnResult()
            .responseBody

        assertEqualsCars(convertToCarResponse(expectedResponse), actualResponse!!.first())
    }

    @Test
    fun `should find cars by rating ranges`(){
        val minValue = 3.1
        val maxValue = 4.0
        val expectedResponse = carResult.find { it.ncapRating < maxValue && it.ncapRating > minValue }!!
        `when`(carService.findByNcapRating(minValue, maxValue)).thenReturn(Flux.just(expectedResponse))

        val actualResponse = webTestClient.get()
            .uri("/api/car/ncap-rating/${minValue}/${maxValue}")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<CarResponse>()
            .returnResult()
            .responseBody

        assertEqualsCars(convertToCarResponse(expectedResponse), actualResponse!!.first())
    }

    @Test
    fun `should find cars by brand`(){
        val brand = "Tata"
        val expectedResponse = carResult.find { it.brand == brand }!!
        `when`(carService.findByBrand(brand)).thenReturn(Flux.just(expectedResponse))

        val actualResponse = webTestClient.get()
            .uri("/api/car/brand/${brand}")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<CarResponse>()
            .returnResult()
            .responseBody

        assertEqualsCars(convertToCarResponse(expectedResponse), actualResponse!!.first())
    }

    @Test
    fun `should find cars by ncap rating range`(){
        val minValue = 4.3
        val maxValue = 4.6
        val expectedResponse = carResult.find { it.rating < maxValue && it.rating > minValue }!!
        `when`(carService.findByRatings(minValue, maxValue)).thenReturn(Flux.just(expectedResponse))

        val actualResponse = webTestClient.get()
            .uri("/api/car/rating/${minValue}/${maxValue}")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<CarResponse>()
            .returnResult()
            .responseBody

        assertEqualsCars(convertToCarResponse(expectedResponse), actualResponse!!.first())
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

    private fun convertToCarResponse(car: Car) = CarResponse.fromEntity(car)

    private fun assertEqualsCars(expected: CarResponse, actual: CarResponse) {
        Assertions.assertEquals(expected.id, actual.id)
        Assertions.assertEquals(expected.brand, actual.brand)
        Assertions.assertEquals(expected.images, actual.images)
        Assertions.assertEquals(expected.name, actual.name)
        Assertions.assertEquals(expected.fuelType, actual.fuelType)
        Assertions.assertEquals(expected.description, actual.description)
        Assertions.assertEquals(expected.ncapRating, actual.ncapRating)
        Assertions.assertEquals(expected.rating, actual.rating)
    }
}