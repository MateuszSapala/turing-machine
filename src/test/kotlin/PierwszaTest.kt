import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pl.lodz.uni.project1.Pierwsza
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class PierwszaTest {
    @ParameterizedTest
    @MethodSource("testPierwszaData")
    fun testPierwsza(liczba: Int) {
        assertDoesNotThrow(Exec(liczba))
    }

    private fun testPierwszaData(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(2),
            Arguments.of(3),
            Arguments.of(5),
            Arguments.of(7),
            Arguments.of(11),
            Arguments.of(13),
        )
    }

    @ParameterizedTest
    @MethodSource("testNiePierwszaData")
    fun testNiePierwsza(liczba: Int) {
        assertThrows(IllegalArgumentException().javaClass,Exec(liczba))
    }

    private fun testNiePierwszaData(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(4),
            Arguments.of(6),
            Arguments.of(8),
            Arguments.of(9),
            Arguments.of(10),
            Arguments.of(12),
            Arguments.of(14),
        )
    }

    private class Exec(val liczba: Int) : Executable {
        override fun execute() {
            Pierwsza.liczbaPierwsza(liczba)
        }
    }
}