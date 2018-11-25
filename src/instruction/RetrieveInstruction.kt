package instruction

import Location

class RetrieveInstruction(val from: Location, val tsu: String, var completed: Boolean, val priority: Priority)