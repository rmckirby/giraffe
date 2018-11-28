package service

import domain.Aisle
import domain.Coordinates
import domain.Location

class LocationService {

    val aisle1Locations = mapOf(
        "BULK.PND.A" to Location("BULK.PND.A", "A", Coordinates(35.0, -20.0), true),
        "BULK.A.01" to Location("BULK.A.01", "A", Coordinates(10.0, 290.0), true),
        "BULK.A.02" to Location("BULK.A.02", "A", Coordinates(60.0, 290.0), true),
        "BULK.A.03" to Location("BULK.A.03", "A", Coordinates(10.0, 280.0), true),
        "BULK.A.04" to Location("BULK.A.04", "A", Coordinates(60.0, 280.0), true),
        "BULK.A.05" to Location("BULK.A.05", "A", Coordinates(10.0, 270.0), true),
        "BULK.A.06" to Location("BULK.A.06", "A", Coordinates(60.0, 270.0), true),
        "BULK.A.07" to Location("BULK.A.07", "A", Coordinates(10.0, 260.0), true),
        "BULK.A.08" to Location("BULK.A.08", "A", Coordinates(60.0, 260.0), true),
        "BULK.A.09" to Location("BULK.A.09", "A", Coordinates(10.0, 250.0), true),
        "BULK.A.10" to Location("BULK.A.10", "A", Coordinates(60.0, 250.0), true),
        "BULK.A.11" to Location("BULK.A.11", "A", Coordinates(10.0, 240.0), true),
        "BULK.A.12" to Location("BULK.A.12", "A", Coordinates(60.0, 240.0), true),
        "BULK.A.13" to Location("BULK.A.13", "A", Coordinates(10.0, 230.0), true),
        "BULK.A.14" to Location("BULK.A.14", "A", Coordinates(60.0, 230.0), true),
        "BULK.A.15" to Location("BULK.A.15", "A", Coordinates(10.0, 220.0), true),
        "BULK.A.16" to Location("BULK.A.16", "A", Coordinates(60.0, 220.0), true),
        "BULK.A.17" to Location("BULK.A.17", "A", Coordinates(10.0, 210.0), true),
        "BULK.A.18" to Location("BULK.A.18", "A", Coordinates(60.0, 210.0), true),
        "BULK.A.19" to Location("BULK.A.19", "A", Coordinates(10.0, 200.0), true),
        "BULK.A.20" to Location("BULK.A.20", "A", Coordinates(60.0, 200.0), true),
        "BULK.A.21" to Location("BULK.A.21", "A", Coordinates(10.0, 190.0), true),
        "BULK.A.22" to Location("BULK.A.22", "A", Coordinates(60.0, 190.0), true),
        "BULK.A.23" to Location("BULK.A.23", "A", Coordinates(10.0, 180.0), true),
        "BULK.A.24" to Location("BULK.A.24", "A", Coordinates(60.0, 180.0), true),
        "BULK.A.25" to Location("BULK.A.25", "A", Coordinates(10.0, 170.0), true),
        "BULK.A.26" to Location("BULK.A.26", "A", Coordinates(60.0, 170.0), false),
        "BULK.A.27" to Location("BULK.A.27", "A", Coordinates(10.0, 160.0), false),
        "BULK.A.28" to Location("BULK.A.28", "A", Coordinates(60.0, 160.0), false),
        "BULK.A.29" to Location("BULK.A.29", "A", Coordinates(10.0, 150.0), false),
        "BULK.A.30" to Location("BULK.A.30", "A", Coordinates(60.0, 150.0), false),
        "BULK.A.31" to Location("BULK.A.31", "A", Coordinates(10.0, 140.0), false),
        "BULK.A.32" to Location("BULK.A.32", "A", Coordinates(60.0, 140.0), false),
        "BULK.A.33" to Location("BULK.A.33", "A", Coordinates(10.0, 130.0), false),
        "BULK.A.34" to Location("BULK.A.34", "A", Coordinates(60.0, 130.0), false),
        "BULK.A.35" to Location("BULK.A.35", "A", Coordinates(10.0, 120.0), true),
        "BULK.A.36" to Location("BULK.A.36", "A", Coordinates(60.0, 120.0), true),
        "BULK.A.37" to Location("BULK.A.37", "A", Coordinates(10.0, 110.0), true),
        "BULK.A.38" to Location("BULK.A.38", "A", Coordinates(60.0, 110.0), true),
        "BULK.A.39" to Location("BULK.A.39", "A", Coordinates(10.0, 100.0), true),
        "BULK.A.40" to Location("BULK.A.40", "A", Coordinates(60.0, 100.0), true),
        "BULK.A.41" to Location("BULK.A.41", "A", Coordinates(10.0, 90.0), true),
        "BULK.A.42" to Location("BULK.A.42", "A", Coordinates(60.0, 90.0), true),
        "BULK.A.43" to Location("BULK.A.43", "A", Coordinates(10.0, 80.0), true),
        "BULK.A.44" to Location("BULK.A.44", "A", Coordinates(60.0, 80.0), true),
        "BULK.A.45" to Location("BULK.A.45", "A", Coordinates(10.0, 70.0), true),
        "BULK.A.46" to Location("BULK.A.46", "A", Coordinates(60.0, 70.0), true),
        "BULK.A.47" to Location("BULK.A.47", "A", Coordinates(10.0, 60.0), true),
        "BULK.A.48" to Location("BULK.A.48", "A", Coordinates(60.0, 60.0), true),
        "BULK.A.49" to Location("BULK.A.49", "A", Coordinates(10.0, 50.0), true),
        "BULK.A.50" to Location("BULK.A.50", "A", Coordinates(60.0, 50.0), true),
        "BULK.A.51" to Location("BULK.A.51", "A", Coordinates(10.0, 40.0), true),
        "BULK.A.52" to Location("BULK.A.52", "A", Coordinates(60.0, 40.0), true),
        "BULK.A.53" to Location("BULK.A.53", "A", Coordinates(10.0, 30.0), true),
        "BULK.A.54" to Location("BULK.A.54", "A", Coordinates(60.0, 30.0), true),
        "BULK.A.55" to Location("BULK.A.55", "A", Coordinates(10.0, 20.0), true),
        "BULK.A.56" to Location("BULK.A.56", "A", Coordinates(60.0, 20.0), true),
        "BULK.A.57" to Location("BULK.A.57", "A", Coordinates(10.0, 10.0), true),
        "BULK.A.58" to Location("BULK.A.58", "A", Coordinates(60.0, 10.0), true)
    )

    val aisle2Locations = mapOf(
        "BULK.PND.B" to Location("BULK.PND.B", "B", Coordinates(92.5, -20.0), true),
        "BULK.B.01" to Location("BULK.B.01", "B", Coordinates(70.0, 290.0), false),
        "BULK.B.03" to Location("BULK.B.03", "B", Coordinates(70.0, 280.0), false),
        "BULK.B.05" to Location("BULK.B.05", "B", Coordinates(70.0, 270.0), false),
        "BULK.B.07" to Location("BULK.B.07", "B", Coordinates(70.0, 260.0), false),
        "BULK.B.09" to Location("BULK.B.09", "B", Coordinates(70.0, 250.0), false),
        "BULK.B.11" to Location("BULK.B.11", "B", Coordinates(70.0, 240.0), false),
        "BULK.B.13" to Location("BULK.B.13", "B", Coordinates(70.0, 230.0), false),
        "BULK.B.15" to Location("BULK.B.15", "B", Coordinates(70.0, 220.0), false),
        "BULK.B.17" to Location("BULK.B.17", "B", Coordinates(70.0, 210.0), false),
        "BULK.B.19" to Location("BULK.B.19", "B", Coordinates(70.0, 200.0), false),
        "BULK.B.21" to Location("BULK.B.21", "B", Coordinates(70.0, 190.0), false),
        "BULK.B.23" to Location("BULK.B.23", "B", Coordinates(70.0, 180.0), false),
        "BULK.B.25" to Location("BULK.B.25", "B", Coordinates(70.0, 170.0), false),
        "BULK.B.27" to Location("BULK.B.27", "B", Coordinates(70.0, 160.0), false),
        "BULK.B.29" to Location("BULK.B.29", "B", Coordinates(70.0, 150.0), false),
        "BULK.B.31" to Location("BULK.B.31", "B", Coordinates(70.0, 140.0), false),
        "BULK.B.33" to Location("BULK.B.33", "B", Coordinates(70.0, 130.0), false),
        "BULK.B.35" to Location("BULK.B.35", "B", Coordinates(70.0, 120.0), false),
        "BULK.B.37" to Location("BULK.B.37", "B", Coordinates(70.0, 110.0), false),
        "BULK.B.39" to Location("BULK.B.39", "B", Coordinates(70.0, 100.0), false),
        "BULK.B.41" to Location("BULK.B.41", "B", Coordinates(70.0, 90.0), false),
        "BULK.B.43" to Location("BULK.B.43", "B", Coordinates(70.0, 80.0), false),
        "BULK.B.45" to Location("BULK.B.45", "B", Coordinates(70.0, 70.0), false),
        "BULK.B.47" to Location("BULK.B.47", "B", Coordinates(70.0, 60.0), false),
        "BULK.B.49" to Location("BULK.B.49", "B", Coordinates(70.0, 50.0), false),
        "BULK.B.51" to Location("BULK.B.51", "B", Coordinates(70.0, 40.0), false),
        "BULK.B.53" to Location("BULK.B.53", "B", Coordinates(70.0, 30.0), false),
        "BULK.B.55" to Location("BULK.B.55", "B", Coordinates(70.0, 20.0), false),
        "BULK.B.57" to Location("BULK.B.57", "B", Coordinates(70.0, 10.0), false),
        "BULK.B.02" to Location("BULK.B.02", "B", Coordinates(120.0, 290.0), false),
        "BULK.B.04" to Location("BULK.B.04", "B", Coordinates(120.0, 280.0), false),
        "BULK.B.06" to Location("BULK.B.06", "B", Coordinates(120.0, 270.0), false),
        "BULK.B.08" to Location("BULK.B.08", "B", Coordinates(120.0, 260.0), false),
        "BULK.B.10" to Location("BULK.B.10", "B", Coordinates(120.0, 250.0), false),
        "BULK.B.12" to Location("BULK.B.12", "B", Coordinates(120.0, 240.0), false),
        "BULK.B.14" to Location("BULK.B.14", "B", Coordinates(120.0, 230.0), false),
        "BULK.B.16" to Location("BULK.B.16", "B", Coordinates(120.0, 220.0), false),
        "BULK.B.18" to Location("BULK.B.18", "B", Coordinates(120.0, 210.0), false),
        "BULK.B.20" to Location("BULK.B.20", "B", Coordinates(120.0, 200.0), false),
        "BULK.B.22" to Location("BULK.B.22", "B", Coordinates(120.0, 190.0), false),
        "BULK.B.24" to Location("BULK.B.24", "B", Coordinates(120.0, 180.0), false),
        "BULK.B.26" to Location("BULK.B.26", "B", Coordinates(120.0, 170.0), false),
        "BULK.B.28" to Location("BULK.B.28", "B", Coordinates(120.0, 160.0), false),
        "BULK.B.30" to Location("BULK.B.30", "B", Coordinates(120.0, 150.0), false),
        "BULK.B.32" to Location("BULK.B.32", "B", Coordinates(120.0, 140.0), false),
        "BULK.B.34" to Location("BULK.B.34", "B", Coordinates(120.0, 130.0), false),
        "BULK.B.36" to Location("BULK.B.36", "B", Coordinates(120.0, 120.0), false),
        "BULK.B.38" to Location("BULK.B.38", "B", Coordinates(120.0, 110.0), false),
        "BULK.B.40" to Location("BULK.B.40", "B", Coordinates(120.0, 100.0), false),
        "BULK.B.42" to Location("BULK.B.42", "B", Coordinates(120.0, 90.0), false),
        "BULK.B.44" to Location("BULK.B.44", "B", Coordinates(120.0, 80.0), false),
        "BULK.B.46" to Location("BULK.B.46", "B", Coordinates(120.0, 70.0), false),
        "BULK.B.48" to Location("BULK.B.48", "B", Coordinates(120.0, 60.0), false),
        "BULK.B.50" to Location("BULK.B.50", "B", Coordinates(120.0, 50.0), false),
        "BULK.B.52" to Location("BULK.B.52", "B", Coordinates(120.0, 40.0), false),
        "BULK.B.54" to Location("BULK.B.54", "B", Coordinates(120.0, 30.0), false),
        "BULK.B.56" to Location("BULK.B.56", "B", Coordinates(120.0, 20.0), false),
        "BULK.B.58" to Location("BULK.B.58", "B", Coordinates(120.0, 10.0), false)
    )

    val aisles = mapOf(
        "A" to Aisle(
            "A",
            Coordinates(10.0, 0.0), Coordinates(10.0, 300.0),
            Coordinates(60.0, 0.0), Coordinates(60.0, 300.0),
            Coordinates(35.0, 310.0),
            aisle1Locations.values.toSet()
        ),
        "B" to Aisle(
            "B",
            Coordinates(70.0, 0.0), Coordinates(70.0, 300.0),
            Coordinates(120.0, 0.0), Coordinates(120.0, 300.0),
            Coordinates(92.5, 310.0),
            aisle1Locations.values.toSet()
        )
    )

    val pnds = mapOf(
        "A" to Location("BULK.PND.A", "A", Coordinates(35.0, -20.0), true),
        "B" to Location("BULK.PND.B", "B", Coordinates(92.5, -20.0), true)
    )

    fun getAllLocations(): Map<String, Location> {
        val locations = HashMap<String, Location>()
        locations.putAll(aisle1Locations)
        locations.putAll(aisle2Locations)
        return locations
    }

}