package service

import domain.Aisle
import domain.Coordinates
import domain.Location
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object StoreServiceTest : Spek({

    val locationService = mockk<LocationService>()
    val onTest = StoreService(locationService)


    Feature("Storing a TSU in the VNA") {

        Scenario("VNA has empty locations in the same aisle") {

            val pnd = Location("P&D", "A", Coordinates(5.0, 5.0), true)
            lateinit var result: Location

            Given("Two empty locations in Aisle A") {
                val locations = setOf(
                    Location("loc-01", "A", Coordinates(10.0, 10.0), false),
                    Location("loc-02", "A", Coordinates(10.0, 20.0), false)
                )
                every { locationService.aisles } returns mapOf("A" to Aisle(name = "A", locations = locations))
            }

            When("Requesting a storage location location the Aisle A P&D") {
                result = onTest.calculateClosestEmptyLocationInAisle(pnd)
            }

            Then("Result is the closest available storage location") {
                assertEquals("loc-01", result.name)
                assertEquals("A", result.aisle)
                assertFalse(result.hasTsu)
            }

        }

        Scenario("VNA does not have empty locations in the same aisle") {

            val pnd = Location("P&D", "A", Coordinates(5.0, 5.0), true)
            lateinit var result: Location

            Given("No empty locations in Aisle A but 3 empty in Aisle B") {
                val locationsForAisle1 = setOf(
                    Location("loc-01", "A", Coordinates(10.0, 10.0), true),
                    Location("loc-02", "A", Coordinates(10.0, 20.0), true)
                )
                val locationsForAisle2 = setOf(
                    Location("loc-03", "B", Coordinates(30.0, 10.0), false),
                    Location("loc-04", "B", Coordinates(30.0, 20.0), false)
                )
                every { locationService.getAllLocations() } returns mapOf(
                    "loc-01" to Location("loc-01", "A", Coordinates(10.0, 10.0), true),
                    "loc-02" to Location("loc-02", "A", Coordinates(10.0, 20.0), true),
                    "loc-03" to Location("loc-03", "B", Coordinates(30.0, 10.0), false),
                    "loc-04" to Location("loc-04", "B", Coordinates(30.0, 20.0), false)
                )
                every { locationService.aisles } returns mapOf(
                    "A" to Aisle(name = "A", locations = locationsForAisle1),
                    "B" to Aisle(name = "B", locations = locationsForAisle2)
                )
            }

            When("Requesting a storage location location the Aisle A P&D") {
                result = onTest.calculateClosestEmptyLocationInAisle(pnd)
            }

            Then("Result is the closest available storage location") {
                assertEquals("loc-03", result.name)
                assertEquals("B", result.aisle)
                assertFalse(result.hasTsu)
            }

        }

    }

})