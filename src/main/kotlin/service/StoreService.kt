package service

import domain.Location

class StoreService(private val locationService: LocationService) {

    fun calculateClosestEmptyLocation(from: Location): Location {
        val location = locationService.getAllLocations().values
            .filterNot { it.name == from.name }
            .filterNot { it.hasTsu }
            .minBy { from.distanceBetween(it) } ?: throw Exception("Could not find location")
        location.hasTsu = true
        return location
    }
}