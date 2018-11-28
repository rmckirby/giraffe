package instruction

import domain.Coordinates

data class MoveInstruction(val coordinates: Coordinates,
                      val instructionTitle: String? = null,
                      val instructionLocation: String? = null,
                      val moveType: MoveType)
