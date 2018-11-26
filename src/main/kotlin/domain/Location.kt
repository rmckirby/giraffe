package domain

class Location(val name: String, val aisle: String, val coords: Coordinates, var hasTsu: Boolean) {

    fun distanceBetween(location: Location): Double {
        val x = Math.pow(location.coords.x - this.coords.x, 2.0)
        val y = Math.pow(location.coords.y - this.coords.y, 2.0)
        return Math.sqrt(x + y)
    }

    fun isBehind(location: Location) = this.coords.y < location.coords.y

}