import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MasterMindTests {

    @Test
    fun createRandom4Digit_1() {
        assertEquals(4, createRandom4Digit().toList().distinct().size)
    }

    @Test
    fun mastermindEval_1() {
        val eval = mastermindEval("1234", "8576")
        assertEquals(eval[0], 0)
        assertEquals(eval[1], 0)
    }

    @Test
    fun mastermindEval_2() {
        val eval = mastermindEval("5678", "8576")
        assertEquals(eval[0], 4)
        assertEquals(eval[1], 1)
    }

    @Test
    fun mastermindEval_3() {
        val eval = mastermindEval("5555", "8576")
        assertEquals(eval[0], 1)
        assertEquals(eval[1], 1)
    }

    @Test
    fun mastermindEval_4() {
        val eval = mastermindEval("3586", "8576")
        assertEquals(eval[0], 3)
        assertEquals(eval[1], 2)
    }
}