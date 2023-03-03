import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.lodz.uni.project1.turing.Turing
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TuringTest {
    @ParameterizedTest
    @MethodSource("testCheckIfNumberIsPrimeData")
    fun testCheckIfNumberIsPrime(number: Int) {
        assertDoesNotThrow(Exec(number))
    }

    private fun testCheckIfNumberIsPrimeData(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(2),
            Arguments.of(3),
            Arguments.of(5),
            Arguments.of(7),
            Arguments.of(11),
            Arguments.of(13),
            Arguments.of(17),
            Arguments.of(19),
            Arguments.of(23),
        )
    }

    @ParameterizedTest
    @MethodSource("testCheckIfNumberIsPrimeDataThrowData")
    fun testCheckIfNumberIsPrimeForNotPrime(number: Int) {
        assertThrows(IllegalArgumentException().javaClass, Exec(number))
    }

    private fun testCheckIfNumberIsPrimeDataThrowData(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(4),
            Arguments.of(6),
            Arguments.of(8),
            Arguments.of(9),
            Arguments.of(10),
            Arguments.of(12),
            Arguments.of(14),
            Arguments.of(55),
            Arguments.of(99),
        )
    }

    private class Exec(val number: Int) : Executable {
        override fun execute() {
            Turing(number).checkIfNumberIsPrime()
        }
    }
}