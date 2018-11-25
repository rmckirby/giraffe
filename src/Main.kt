import instruction.Priority
import instruction.RetrieveInstruction
import instruction.StoreInstruction
import service.LocationService
import service.RetrieveMode
import service.RetrieveService
import service.StoreService

fun main(args: Array<String>) {

    val locationService = LocationService()
    val storeService = StoreService(locationService)

    val storeInstructions = listOf(
        StoreInstruction(locationService.aisle1Locations["BULK.PND.A"]!!, "tsu-01", false),
        StoreInstruction(locationService.aisle1Locations["BULK.PND.A"]!!, "tsu-02", false)
    )

    val retrieveInstructions = listOf(
        RetrieveInstruction(locationService.aisle1Locations["BULK.A.01"]!!, "tsu-06", false, Priority.LOW),
        RetrieveInstruction(locationService.aisle1Locations["BULK.A.02"]!!, "tsu-07", false, Priority.MEDIUM),
        RetrieveInstruction(locationService.aisle1Locations["BULK.A.08"]!!, "tsu-08", false, Priority.NDD)
    )

    val retrieveService = RetrieveService(retrieveInstructions, locationService, RetrieveMode.HIGHEST_PRIORITY_ON_WAY_OUT)

    for (instruction in storeInstructions) {
        val location = storeService.calculateClosestEmptyLocation(instruction.from)
        println("STORE: ${instruction.tsu} from ${instruction.from.name} -> ${location.name}")

        retrieveService.calculateRetrieveInstruction(location) ?. let {
            it.from.hasTsu = false
            it.completed = true
            println("RETRIEVE: ${instruction.tsu} from ${it.from.name} -> P&D")
        }
    }

    retrieveInstructions
        .filterNot { it.completed }
        .forEach { println("RETRIEVE: ${it.tsu} from ${it.from.name} -> P&D") }

}