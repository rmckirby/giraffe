package service

import domain.Location
import exception.AisleNotFoundException
import instruction.RetrieveInstruction

class RetrieveService(private val locationService: LocationService) {

    fun calculateRetrieveInstruction(startLocation: Location, mode: RetrieveMode, retrieveInstructions: List<RetrieveInstruction>): RetrieveInstruction? =
        when (mode) {
            RetrieveMode.CLOSEST_IN_AISLE -> calculateClosestRetrieveInstructionInAisle(startLocation, retrieveInstructions)
            RetrieveMode.CLOSEST_AVAILABLE -> calculateClosestRetrieveInstructionAvailable(startLocation, retrieveInstructions)
            RetrieveMode.HIGHEST_PRIORITY_IN_AISLE -> calculateRetrieveInstructionWithHighestPriorityInAisle(startLocation, retrieveInstructions)
            RetrieveMode.HIGHEST_PRIORITY_AVAILABLE -> calculateClosestRetrieveInstructionWithHighestPriority(startLocation, retrieveInstructions)
            RetrieveMode.HIGHEST_PRIORITY_ON_WAY_OUT -> calculateRetrieveInstructionWithHighestPriorityBehind(startLocation, retrieveInstructions)
        }

    private fun calculateClosestRetrieveInstructionInAisle(start: Location, retrieveInstructions: List<RetrieveInstruction>): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw AisleNotFoundException(start.aisle)
        return retrieveInstructions
            .filter { aisle.locations.contains(it.location) }
            .filterNot { it.completed }
            .sortedBy { start.distanceBetween(it.location) }
            .firstOrNull()
    }

    private fun calculateClosestRetrieveInstructionAvailable(start: Location, retrieveInstructions: List<RetrieveInstruction>): RetrieveInstruction? {
        return retrieveInstructions
            .filterNot { it.completed }
            .sortedBy { start.distanceBetween(it.location) }
            .firstOrNull()
    }

    private fun calculateRetrieveInstructionWithHighestPriorityInAisle(start: Location, retrieveInstructions: List<RetrieveInstruction>): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw AisleNotFoundException(start.aisle)

        return retrieveInstructions
            .filter { aisle.locations.contains(it.location) }
            .filterNot { it.completed }
            .sortedBy { it.priority }
            .lastOrNull()
    }

    private fun calculateClosestRetrieveInstructionWithHighestPriority(start: Location, retrieveInstructions: List<RetrieveInstruction>): RetrieveInstruction? {
        return retrieveInstructions
            .filterNot { it.completed }
            .sortedWith(compareBy({ it.priority }, { start.distanceBetween(it.location) }))
            .lastOrNull()
    }

    private fun calculateRetrieveInstructionWithHighestPriorityBehind(start: Location, retrieveInstructions: List<RetrieveInstruction>): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw AisleNotFoundException(start.aisle)
        return retrieveInstructions
            .filter { aisle.locations.contains(it.location) }
            .filter { it.location.isBehind(start) }
            .sortedBy { it.priority }
            .lastOrNull()
    }
}