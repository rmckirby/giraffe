package instruction

import domain.Location

class RetrieveInstruction(val location: Location, val tsu: String, var completed: Boolean, val priority: Priority)