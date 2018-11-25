package service

import domain.Aisle
import domain.Coordinates
import domain.Location

class LocationService {

    val aisle1Locations = mapOf(
        "BULK.PND.A" to Location("BULK.PND.A", "A", Coordinates(1.5, .5), false),
        "BULK.A.01" to Location("BULK.A.01", "A", Coordinates(1.0, 1.0), false),
        "BULK.A.03" to Location("BULK.A.03", "A", Coordinates(1.0, 1.5), false),
        "BULK.A.05" to Location("BULK.A.05", "A", Coordinates(1.0, 2.0), false),
        "BULK.A.07" to Location("BULK.A.07", "A", Coordinates(1.0, 2.5), false),
        "BULK.A.02" to Location("BULK.A.02", "A", Coordinates(2.0, 1.0), false),
        "BULK.A.04" to Location("BULK.A.04", "A", Coordinates(2.0, 1.5), false),
        "BULK.A.06" to Location("BULK.A.06", "A", Coordinates(2.0, 2.0), false),
        "BULK.A.08" to Location("BULK.A.08", "A", Coordinates(2.0, 2.5), false)
    )

    val aisle2Locations = mapOf(
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

    val aisles = mapOf(
        "A" to Aisle("A", aisle1Locations.values.toSet()),
        "B" to Aisle("B", aisle2Locations.values.toSet())
    )

    fun getAllLocations(): Map<String, Location> {
        val locations = HashMap<String, Location>()
        locations.putAll(aisle1Locations)
        locations.putAll(aisle2Locations)
        return locations
    }

}