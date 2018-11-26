package instruction

import domain.Location

class StoreInstruction(val from: Location, val tsu: String, var completed: Boolean)