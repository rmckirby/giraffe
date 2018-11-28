package domain

class Aisle(val name: String,
            val leftWallStart: Coordinates,
            val leftWallEnd: Coordinates,
            val rightWallStart: Coordinates,
            val rightWallEnd: Coordinates,
            val label: Coordinates,
            val locations: Set<Location>)