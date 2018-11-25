package service

import domain.Location
import instruction.RetrieveInstruction

class RetrieveService(private val retrieveInstructions: List<RetrieveInstruction>,
                      private val locationService: LocationService) {

    fun calculateClosestRetrieveInstructionInAisle(start: Location): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")

        return retrieveInstructions
            .filter { aisle.locations.contains(it.from) }
            .filterNot { it.completed }
            .sortedBy { start.distanceBetween(it.from) }
            .firstOrNull()
    }

    fun calculateClosestRetrieveInstructionAvailable(start: Location): RetrieveInstruction? {
        return retrieveInstructions
            .filterNot { it.completed }
            .sortedBy { start.distanceBetween(it.from) }
            .firstOrNull()
    }

    fun calculateRetrieveInstructionWithHighestPriorityInAisle(start: Location): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")

        return retrieveInstructions
            .filter { aisle.locations.contains(it.from) }
            .filterNot { it.completed }
            .sortedBy { it.priority }
            .lastOrNull()
    }

    fun calculateClosestRetrieveInstructionWithHighestPriority(start: Location): RetrieveInstruction? {
        return retrieveInstructions
            .filterNot { it.completed }
            .sortedWith(compareBy({ it.priority }, { start.distanceBetween(it.from) }))
            .lastOrNull()
    }

    fun calculateRetrieveInstructionWithHighestPriorityBehind(start: Location): RetrieveInstruction? {
        val aisle = locationService.aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")
        return retrieveInstructions
            .filter { aisle.locations.contains(it.from) }
            .filter { it.from.isBehind(start)  }
            .sortedBy { it.priority }
            .lastOrNull()
    }
}