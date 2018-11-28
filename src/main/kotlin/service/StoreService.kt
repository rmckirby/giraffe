package service

import domain.Location

class StoreService(private val locationService: LocationService) {

    fun calculateEmptyLocationInAisle(from: Location): Location {
        val aisle = locationService.aisles[from.aisle] ?: throw Exception("Aisle is not good")

        val location = locationService.getAllLocations().values
            .filterNot { it.name == from.name }
            .filterNot { it.hasTsu }
            .filter { it.aisle == aisle.name }
            .minBy { from.distanceBetween(it) } ?: calculateClosestEmptyLocation(from)

        location.hasTsu = true
        return location
    }

    fun calculateClosestEmptyLocation(from: Location): Location {
        val location = locationService.getAllLocations().values
            .filterNot { it.name == from.name }
            .filterNot { it.hasTsu }
            .minBy { from.distanceBetween(it) } ?: throw Exception("Could not find location")
        location.hasTsu = true
        return location
    }
}