package ui

import domain.Coordinates
import domain.Location
import instruction.MoveInstruction
import instruction.MoveType
import instruction.Priority
import instruction.RetrieveInstruction
import instruction.StoreInstruction
import javafx.animation.Animation
import javafx.animation.Interpolator
import javafx.animation.SequentialTransition
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import service.LocationService
import service.RetrieveMode
import service.RetrieveService
import service.StoreService
import tornadofx.*

class VnaView : View() {

    private val locationService: LocationService = LocationService()
    private val movements = mutableListOf<MoveInstruction>()
    private val MODE = RetrieveMode.CLOSEST_IN_AISLE

    private val storeInstructions = listOf(
        StoreInstruction(locationService.aisle1Locations["BULK.PND.A"]!!, "tsu-01", false),
        StoreInstruction(locationService.aisle1Locations["BULK.PND.A"]!!, "tsu-01", false)
    )

    private val retrieveInstructions = listOf(
        RetrieveInstruction(locationService.aisle1Locations["BULK.A.25"]!!, "tsu-06", false, Priority.LOW),
        RetrieveInstruction(locationService.aisle2Locations["BULK.B.25"]!!, "tsu-06", false, Priority.LOW)
    )

    init {
        var pndCoords = locationService.pnds["A"]!!.coords

        val storeService = StoreService(locationService)
        val retrieveService = RetrieveService(retrieveInstructions, locationService, MODE)

        val storageLocations = mutableListOf<Location>()
        lateinit var lastVisitedLocation: Location

        for (instruction in storeInstructions) {

            val location = storeService.calculateClosestEmptyLocationInAisle(instruction.from)
            location.hasTsu = true
            storageLocations.add(location)

            movements.add(MoveInstruction(
                coordinates = Coordinates(pndCoords.x, location.coords.y),
                instructionTitle = "Next - Store: ${instruction.from.name} -> ${location.name}",
                moveType = MoveType.ADJUSTMENT))
            movements.add(MoveInstruction(
                coordinates = location.coords,
                moveType = MoveType.STORE,
                instructionLocation = location.name))
            movements.add(MoveInstruction(coordinates = Coordinates(pndCoords.x, location.coords.y), moveType = MoveType.ADJUSTMENT))

            lastVisitedLocation = location

            retrieveService.calculateRetrieveInstruction(location) ?. let {
                it.from.hasTsu = false
                it.completed = true

                if (lastVisitedLocation.aisle != it.from.aisle) {
                    val otherPnd = locationService.pnds[it.from.aisle]!!.coords
                    movements.add(MoveInstruction(coordinates = pndCoords, moveType = MoveType.ADJUSTMENT))
                    movements.add(MoveInstruction(coordinates = otherPnd, moveType = MoveType.ADJUSTMENT))
                    pndCoords = otherPnd
                }

                movements.add(MoveInstruction(
                    coordinates = Coordinates(pndCoords.x, it.from.coords.y),
                    moveType = MoveType.ADJUSTMENT,
                    instructionTitle = "Next - Retrieve: ${it.from.name} -> P&D"))
                movements.add(MoveInstruction(
                    coordinates = it.from.coords,
                    moveType = MoveType.RETRIEVE,
                    instructionLocation = it.from.name))
                movements.add(MoveInstruction(coordinates = Coordinates(pndCoords.x, it.from.coords.y), moveType = MoveType.RETRIEVE))
                movements.add(MoveInstruction(coordinates = pndCoords, moveType = MoveType.ADJUSTMENT))

                lastVisitedLocation = it.from
            }
        }

        retrieveInstructions
            .filterNot { it.completed }
            .forEach {

                if (lastVisitedLocation.aisle != it.from.aisle) {
                    val otherPnd = locationService.pnds[it.from.aisle]!!.coords
                    movements.add(MoveInstruction(coordinates = pndCoords, moveType = MoveType.ADJUSTMENT))
                    movements.add(MoveInstruction(coordinates = otherPnd, moveType = MoveType.ADJUSTMENT))
                    pndCoords = otherPnd
                }

                movements.add(MoveInstruction(
                    coordinates = Coordinates(pndCoords.x, it.from.coords.y),
                    moveType = MoveType.ADJUSTMENT,
                    instructionTitle = "Next - Retrieve: ${it.from.name} -> P&D"))
                movements.add(MoveInstruction(
                    coordinates = it.from.coords,
                    moveType = MoveType.RETRIEVE,
                    instructionLocation = it.from.name))
                movements.add(MoveInstruction(coordinates = Coordinates(pndCoords.x, it.from.coords.y), moveType = MoveType.RETRIEVE))
                movements.add(MoveInstruction(coordinates = pndCoords, moveType = MoveType.ADJUSTMENT))

                it.completed
                it.from.hasTsu = false

                lastVisitedLocation = it.from
            }

        // Hack to show in the UI that the locations we are storing are initially empty
        storageLocations
            .mapNotNull { locationService.getAllLocations()[it.name] }
            .forEach { it.hasTsu = false }

    }

    override val root = stackpane {

        title = "VNA"
        padding = Insets(40.0, 90.0, 40.0, 90.0)
        minWidth = 600.0
        minHeight = 600.0

        group {

            padding = Insets(0.0, 0.0, 0.0, 15.0)

            // Mode & Instruction Display
            label("Mode: ${MODE.name}") {
                layoutX = 190.0
                layoutY = 10.0
            }

            val currentInstructionTypeLabel = label(movements[0].instructionTitle ?: "") {
                layoutX = 190.0
                layoutY = 30.0
            }

            // Legend
            circle {
                centerX = 310.0
                centerY = 235.0
                fill = Color.ORANGE
                radius = 3.0
            }

            label("Occupied Location") {
                layoutX = 322.0
                layoutY = 227.0
            }

            circle {
                centerX = 310.0
                centerY = 255.0
                fill = Color.PURPLE
                radius = 3.0
            }

            label("Location with retrieve instruction") {
                layoutX = 322.0
                layoutY = 247.0
            }

            circle {
                centerX = 310.0
                centerY = 275.0
                radius = 3.0
            }

            label("Stored TSU") {
                layoutX = 322.0
                layoutY = 267.0
            }

            circle {
                centerX = 310.0
                centerY = 295.0
                radius = 3.0
                fill = Color.AQUAMARINE
            }

            label("Driver") {
                layoutX = 322.0
                layoutY = 287.0
            }

            // The VNA Map
            locationService.aisles.values.forEach {
                line(
                    startX = it.leftWallStart.x, endX = it.leftWallEnd.x,
                    startY = it.leftWallStart.y, endY = it.leftWallEnd.y
                )
                line(
                    startX = it.rightWallStart.x, endX = it.rightWallEnd.x,
                    startY = it.rightWallStart.y, endY = it.rightWallEnd.y
                )
                label(it.name) {
                    layoutX = it.label.x
                    layoutY = it.label.y
                    textFill = Color.DARKGRAY
                }
            }

            locationService.getAllLocations().values.forEach {
                circle {
                    centerX = it.coords.x
                    centerY = it.coords.y
                    radius = 3.0
                    fill = determineLocationColor(it)
                    id = it.name
                    tooltip(it.name)
                }
            }

            val driver = circle {
                radius = 5.0
                centerX = 10.0
                centerY = -20.0
                fill = Color.AQUAMARINE
                tooltip("Driver")
            }

            val arrayOfAnimations = movements.stream()
                .map { moveInstruction ->
                    timeline(false) {
                        keyframe(1.5.seconds) {
                            moveInstruction.instructionTitle ?. let { title ->
                                keyvalue(currentInstructionTypeLabel.textProperty(), title, Interpolator.LINEAR)
                            }
                            keyvalue(driver.centerXProperty(), moveInstruction.coordinates.x, Interpolator.LINEAR)
                            keyvalue(driver.centerYProperty(), moveInstruction.coordinates.y, Interpolator.LINEAR)
                        }

                    }.then(
                        timeline(false) {
                            moveInstruction.instructionLocation?.let { location ->
                            keyframe(250.millis) {
                                    keyvalue((getLocationShape(location, children) as Circle).fillProperty(),
                                        getColorForMoveInstruction(moveInstruction.moveType), Interpolator.LINEAR)
                                }
                            }
                        }
                    )
                }
                .toArray<Animation> { size -> arrayOfNulls(size) }

            SequentialTransition(*arrayOfAnimations).play()
        }
    }

    private fun determineLocationColor(location: Location): Color =
        when {
            hasLocationRetrieveInstruction(location) -> Color.PURPLE
            location.hasTsu -> Color.ORANGE
            else -> Color.SILVER
        }

    private fun hasLocationRetrieveInstruction(location: Location): Boolean =
        retrieveInstructions.map { it.from.name }.any { it == location.name }

    private fun getLocationShape(location: String, children: List<Node>): Node? = children
        .filter { it is Circle }
        .filter { it.id != null }
        .firstOrNull { it.id == location }

    private fun getColorForMoveInstruction(moveType: MoveType): Color? =
        when(moveType) {
            MoveType.STORE -> Color.GREENYELLOW
            MoveType.RETRIEVE -> Color.SILVER
            MoveType.ADJUSTMENT -> null
        }
}