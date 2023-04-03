package by.innowise.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class IntervalConstructionTest {

    @ParameterizedTest
    @MethodSource("generateData")
    void testIntervalConstruction(String expected, String[] task) {
        Assertions.assertEquals(expected, Intervals.intervalConstruction(task));
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("D", new String[]{"M2", "C", "asc"}),
                Arguments.of("F#", new String[]{"P5", "B"}),
                Arguments.of("A", new String[]{"m2", "Bb", "dsc"}),
                Arguments.of("Abb", new String[]{"M3", "Cb", "dsc"}),
                Arguments.of("D#", new String[]{"P4", "G#", "dsc"}),
                Arguments.of("G#", new String[]{"m3", "B", "dsc"}),
                Arguments.of("Gbb", new String[]{"m2", "Fb", "asc"}),
                Arguments.of("D#", new String[]{"M2", "E#", "dsc"}),
                Arguments.of("B", new String[]{"P4", "E", "dsc"}),
                Arguments.of("E", new String[]{"m2", "D#"}),
                Arguments.of("F#", new String[]{"M7", "G", "asc"}),
                Arguments.of("D", new String[]{"M2", "C", "asc"}),
                Arguments.of("F#", new String[]{"P5", "B", "asc"}),
                Arguments.of("Gbb", new String[]{"m2", "Fb", "asc"})
        );
    }
}
