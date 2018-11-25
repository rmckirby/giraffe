import instruction.Priority
import instruction.RetrieveInstruction
import instruction.StoreInstruction

private val locationsForAisle1 = mapOf(
    "BULK.PND.A" to Location("BULK.PND.A", "A", Coordinates(1.5, .5), false),
    "BULK.A.01" to Location("BULK.A.01", "A", Coordinates(1.0, 1.0), true),
    "BULK.A.03" to Location("BULK.A.03", "A", Coordinates(1.0, 1.5), true),
    "BULK.A.05" to Location("BULK.A.05", "A", Coordinates(1.0, 2.0), false),
    "BULK.A.07" to Location("BULK.A.07", "A", Coordinates(1.0, 2.5), false),
    "BULK.A.02" to Location("BULK.A.02", "A", Coordinates(2.0, 1.0), true),
    "BULK.A.04" to Location("BULK.A.04", "A", Coordinates(2.0, 1.5), true),
    "BULK.A.06" to Location("BULK.A.06", "A", Coordinates(2.0, 2.0), true),
    "BULK.A.08" to Location("BULK.A.08", "A", Coordinates(2.0, 2.5), true)
)

private val locationsForAisle2 = mapOf(
    "BULK.PND.B" to Location("BULK.PND.B", "B", Coordinates(4.5, .5), false),
    "BULK.B.01" to Location("BULK.B.01", "B", Coordinates(4.0, 1.0), false),
    "BULK.B.03" to Location("BULK.B.03", "B", Coordinates(4.0, 1.5), false),
    "BULK.B.05" to Location("BULK.B.05", "B", Coordinates(4.0, 2.0), false),
    "BULK.B.07" to Location("BULK.B.07", "B", Coordinates(4.0, 2.5), false),
    "BULK.B.02" to Location("BULK.B.02", "B", Coordinates(5.0, 1.0), false),
    "BULK.B.04" to Location("BULK.B.04", "B", Coordinates(5.0, 1.5), false),
    "BULK.B.06" to Location("BULK.B.06", "B", Coordinates(5.0, 2.0), false),
    "BULK.B.08" to Location("BULK.B.08", "B", Coordinates(5.0, 2.5), false)
)

private val aisles = mapOf(
    "A" to Aisle("A", locationsForAisle1.values.toSet()),
    "B" to Aisle("B", locationsForAisle2.values.toSet())
)

private val storeInstructions = listOf(
    StoreInstruction(locationsForAisle1["BULK.PND.A"]!!, "tsu-01", false),
    StoreInstruction(locationsForAisle1["BULK.PND.A"]!!, "tsu-02", false)
)

private val retrieveInstructions = listOf(
    RetrieveInstruction(locationsForAisle1["BULK.A.01"]!!, "tsu-06", false, Priority.LOW),
    RetrieveInstruction(locationsForAisle1["BULK.A.02"]!!, "tsu-07", false, Priority.MEDIUM),
    RetrieveInstruction(locationsForAisle1["BULK.A.08"]!!, "tsu-08", false, Priority.NDD)
)

private fun combineAllLocations(): Map<String, Location> {
    val locations = HashMap<String, Location>()
    locations.putAll(locationsForAisle1)
    locations.putAll(locationsForAisle2)
    return locations
}


private fun calculateClosestEmptyLocation(from: Location, locations: Map<String, Location>): Location {
    val location = locations.values
        .filterNot { it.name == from.name }
        .filterNot { it.hasTsu }
        .minBy { from.distanceBetween(it) } ?: throw Exception("Could not find location")
    location.hasTsu = true
    return location
}

private fun calculateClosestRetrieveInstructionInAisle(start: Location): RetrieveInstruction? {
    val aisle = aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")

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
    val aisle = aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")

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
    val aisle = aisles[start.aisle] ?: throw Exception("${start.aisle} is not a valid aisle")
    return retrieveInstructions
        .filter { aisle.locations.contains(it.from) }
        .filter { it.from.isBehind(start)  }
        .sortedBy { it.priority }
        .lastOrNull()
}

fun main(args: Array<String>) {

    val locations = combineAllLocations()

    for (instruction in storeInstructions) {
        val location = calculateClosestEmptyLocation(instruction.from, locations)
        println("STORE: ${instruction.tsu} from ${instruction.from.name} -> ${location.name}")

        calculateRetrieveInstructionWithHighestPriorityBehind(location) ?. let {
            it.from.hasTsu = false
            it.completed = true
            println("RETRIEVE: ${instruction.tsu} from ${it.from.name} -> P&D")
        }
    }

    retrieveInstructions
        .filterNot { it.completed }
        .forEach { println("RETRIEVE: ${it.tsu} from ${it.from.name} -> P&D") }

}