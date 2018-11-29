package exception

import java.lang.RuntimeException

class StoreLocationNotFoundException(message: String) : RuntimeException(message)