package service

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RetrieveServiceTest : Spek({
    describe("A test that will always fail") {

        describe("false") {
            it("is 1 equal to 2?") {
                assertEquals(1, 1)
            }
        }

    }
})