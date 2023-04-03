package by.innowise.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class IntervalIdentificationTest {

    @ParameterizedTest
    @MethodSource("generateData")
    void testIntervalIdentification(String expected, String[] task) {
        Assertions.assertEquals(expected, Intervals.intervalIdentification(task));
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("M2", new String[]{"C", "D"}),
                Arguments.of("P5", new String[]{"B", "F#", "asc"}),
                Arguments.of("m2", new String[]{"Fb", "Gbb"}),
                Arguments.of("M7", new String[]{"G", "F#", "asc"}),
                Arguments.of("m2", new String[]{"Bb", "A", "dsc"}),
                Arguments.of("M3", new String[]{"Cb", "Abb", "dsc"}),
                Arguments.of("P4", new String[]{"G#", "D#", "dsc"}),
                Arguments.of("P4", new String[]{"E", "B", "dsc"}),
                Arguments.of("M2", new String[]{"E#", "D#", "dsc"}),
                Arguments.of("m3", new String[]{"B", "G#", "dsc"})
        );
    }
}
