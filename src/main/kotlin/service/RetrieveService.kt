package service

import domain.Location
import instruction.RetrieveInstruction

class RetrieveService(private val retrieveInstructions: List<RetrieveInstruction>,
                      private val locationService: LocationService,
                      private val mode: RetrieveMode) {

    fun calculateRetrieveInstruction(startLocation: Location): RetrieveInstruction? = when(mode) {
        RetrieveMode.CLOSEST_IN_AISLE -> calculateClosestRetrieveInstructionInAisle(startLocation)
        RetrieveMode.CLOSEST_AVAILABLE -> calculateClosestRetrieveInstructionAvailable(startLocation)
        RetrieveMode.HIGHEST_PRIORITY_IN_AISLE -> calculateRetrieveInstructionWithHighestPriorityInAisle(startLocation)
        RetrieveMode.HIGHEST_PRIORITY_AVAILABLE -> calculateClosestRetrieveInstructionWithHighestPriority(startLocation)
        RetrieveMode.HIGHEST_PRIORITY_ON_WAY_OUT -> calculateRetrieveInstructionWithHighestPriorityBehind(startLocation)
    }

    private fun calculateClosestRetrieveInstructionInAisle(start: Location): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")

        return retrieveInstructions
            .filter { aisle.locations.contains(it.from) }
            .filterNot { it.completed }
            .sortedBy { start.distanceBetween(it.from) }
            .firstOrNull()
    }

    private fun calculateClosestRetrieveInstructionAvailable(start: Location): RetrieveInstruction? {
        return retrieveInstructions
            .filterNot { it.completed }
            .sortedBy { start.distanceBetween(it.from) }
            .firstOrNull()
    }

    private fun calculateRetrieveInstructionWithHighestPriorityInAisle(start: Location): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")

        return retrieveInstructions
            .filter { aisle.locations.contains(it.from) }
            .filterNot { it.completed }
            .sortedBy { it.priority }
            .lastOrNull()
    }

    private fun calculateClosestRetrieveInstructionWithHighestPriority(start: Location): RetrieveInstruction? {
        return retrieveInstructions
            .filterNot { it.completed }
            .sortedWith(compareBy({ it.priority }, { start.distanceBetween(it.from) }))
            .lastOrNull()
    }

    private fun calculateRetrieveInstructionWithHighestPriorityBehind(start: Location): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")
        return retrieveInstructions
            .filter { aisle.locations.contains(it.from) }
            .filter { it.from.isBehind(start)  }
            .sortedBy { it.priority }
            .lastOrNull()
    }
}