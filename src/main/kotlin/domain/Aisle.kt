package domain

class Aisle(val name: String,
            val leftWallStart: Coordinates = Coordinates(0.0, 0.0),
            val leftWallEnd: Coordinates = Coordinates(0.0, 0.0),
            val rightWallStart: Coordinates = Coordinates(0.0, 0.0),
            val rightWallEnd: Coordinates = Coordinates(0.0, 0.0),
            val label: Coordinates = Coordinates(0.0, 0.0),
            val locations: Set<Location>)