package by.innowise.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IntervalConstructionTest {
    List<String[]> taskList;
    List<String> exprectedResultList;

    @BeforeEach
    void setUp() {
        taskList = List.of(
                new String[]{"M2", "C", "asc"},
                new String[]{"P5", "B"},
                new String[]{"m2", "Bb", "dsc"},
                new String[]{"M3", "Cb", "dsc"},
                new String[]{"P4", "G#", "dsc"},
                new String[]{"m3", "B", "dsc"},
                new String[]{"m2", "Fb", "asc"},
                new String[]{"M2", "E#", "dsc"},
                new String[]{"P4", "E", "dsc"},
                new String[]{"m2", "D#"},
                new String[]{"M7", "G", "asc"},
                new String[]{"M2", "C", "asc"},
                new String[]{"P5", "B", "asc"},
                new String[]{"m2", "Fb", "asc"}
        );
        exprectedResultList = List.of("D", "F#", "A", "Abb", "D#", "G#", "Gbb", "D#", "B", "E", "F#", "D", "F#", "Gbb");
    }

    @Test
    void testIntervalConstruction() {
        for (int i = 0; i < taskList.size(); i++) {
            String actualNote = Intervals.intervalConstruction(taskList.get(i));
            String expectedNote = exprectedResultList.get(i);

            Assertions.assertEquals(expectedNote, actualNote);
        }
    }
}
