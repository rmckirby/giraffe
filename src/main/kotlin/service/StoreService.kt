package service

import domain.Location
import exception.AisleNotFoundException
import exception.StoreLocationNotFoundException

class StoreService(private val locationService: LocationService) {

    fun calculateClosestEmptyLocationInAisle(from: Location): Location {
        val aisle = locationService.aisles[from.aisle] ?: throw AisleNotFoundException(from.aisle)
        return aisle.locations
            .filterNot { it.name == from.name }
            .filterNot { it.hasTsu }
            .minBy { from.distanceBetween(it) } ?: calculateClosestEmptyLocation(from)
    }

    fun calculateClosestEmptyLocation(from: Location): Location {
        return locationService.getAllLocations().values
            .filterNot { it.name == from.name }
            .filterNot { it.hasTsu }
            .minBy { from.distanceBetween(it) } ?: throw StoreLocationNotFoundException("Unable to find a free location.")
    }
}