package exception

class AisleNotFoundException(aisle: String) : IllegalArgumentException("$aisle is not a valid location.")