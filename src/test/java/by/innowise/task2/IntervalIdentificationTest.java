package by.innowise.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IntervalIdentificationTest {
    List<String[]> taskList;
    List<String> exprectedResultList;

    @BeforeEach
    void setUp() {
        taskList = List.of(
                new String[]{"C", "D"},
                new String[]{"B", "F#", "asc"},
                new String[]{"Fb", "Gbb"},
                new String[]{"G", "F#", "asc"},
                new String[]{"Bb", "A", "dsc"},
                new String[]{"Cb", "Abb", "dsc"},
                new String[]{"G#", "D#", "dsc"},
                new String[]{"E", "B", "dsc"},
                new String[]{"E#", "D#", "dsc"},
                new String[]{"B", "G#", "dsc"}
        );
        exprectedResultList = List.of("M2", "P5", "m2", "M7", "m2", "M3", "P4", "P4", "M2", "m3");
    }

    @Test
    void testIntervalIdentification() {
        for (int i = 0; i < taskList.size(); i++) {
            String actualInterval = Intervals.intervalIdentification(taskList.get(i));
            String expectedInterval = exprectedResultList.get(i);

            Assertions.assertEquals(expectedInterval, actualInterval);
        }
    }
}
