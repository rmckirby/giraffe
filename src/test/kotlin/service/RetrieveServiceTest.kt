package service

import domain.Aisle
import domain.Coordinates
import domain.Location
import instruction.Priority
import instruction.RetrieveInstruction
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object RetrieveServiceTest : Spek({


    val locationService = mockk<LocationService>()
    val onTest = RetrieveService(locationService)

    Feature("Retrieve a TSU from the VNA") {

        val location1 = Location("loc-01", "A", Coordinates(10.0, 10.0), true)
        val location2 = Location("loc-02", "A", Coordinates(10.0, 20.0), true)
        val location3 = Location("loc-03", "B", Coordinates(20.0, 10.0), true)

        val instructions = listOf(
            RetrieveInstruction(location1, "tsu-01", false, Priority.MEDIUM),
            RetrieveInstruction(location2, "tsu-02", false, Priority.HIGH),
            RetrieveInstruction(location3, "tsu-03", false, Priority.NDD)
        )

        every { locationService.aisles } returns mapOf(
            "A" to Aisle(name = "A", locations = setOf(location1, location2)),
            "B" to Aisle(name = "B", locations = setOf(location3))
        )

        Scenario("Retrieve the closest TSU in the same aisle") {
            lateinit var instruction: RetrieveInstruction
            val startLocation = Location("loc-69", "A", Coordinates(10.0, 13.0), true)

            When("Requesting a Retrieve Instruction") {
                instruction = onTest.calculateRetrieveInstruction(startLocation, RetrieveMode.CLOSEST_IN_AISLE, instructions)!!
            }

            Then("Receive the closest Retrieve instruction in the Aisle") {
                assertEquals(location1, instruction.location)
            }
        }

        Scenario("Retrieve the closest TSU anywhere in the VNA") {
            lateinit var instruction: RetrieveInstruction
            val startLocation = Location("loc-69", "A", Coordinates(10.0, 5.0), true)

            When("Requesting a Retrieve Instruction") {
                instruction = onTest.calculateRetrieveInstruction(startLocation, RetrieveMode.CLOSEST_AVAILABLE, instructions)!!
            }

            Then("Receive the closest Retrieve instruction in the Aisle") {
                assertEquals(location1, instruction.location)
            }
        }

        Scenario("Retrieve the TSU with the highest Priority in the Aisle") {
            lateinit var instruction: RetrieveInstruction
            val startLocation = Location("loc-69", "A", Coordinates(10.0, 13.0), true)

            When("Requesting a Retrieve Instruction") {
                instruction = onTest.calculateRetrieveInstruction(startLocation, RetrieveMode.HIGHEST_PRIORITY_IN_AISLE, instructions)!!
            }

            Then("Receive the closest Retrieve instruction in the Aisle") {
                assertEquals(location2, instruction.location)
            }
        }

        Scenario("Retrieve the TSU with the highest Priority in the VNA") {
            lateinit var instruction: RetrieveInstruction
            val startLocation = Location("loc-69", "A", Coordinates(10.0, 5.0), true)

            When("Requesting a Retrieve Instruction") {
                instruction = onTest.calculateRetrieveInstruction(startLocation, RetrieveMode.HIGHEST_PRIORITY_AVAILABLE, instructions)!!
            }

            Then("Receive the closest Retrieve instruction in the Aisle") {
                assertEquals(location3, instruction.location)
            }
        }

    }

})