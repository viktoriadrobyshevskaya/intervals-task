package by.innowise.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Intervals {
    private static final List<String> NOTE_LIST = List.of("C", "D", "E", "F", "G", "A", "B");
    private static final List<String> ACCEPTABLE_INPUT_NOTES_LIST;
    private static final List<String> ACCEPTABLE_NOTES_LIST;
    private static final Map<String, Map<String, Integer>> INTERVAL_LIST;
    private static final Map<Integer, String> OUTPUT_ACCIDENTALS;
    private static final Map<String, Integer> INPUT_ACCIDENTALS;
    private static final Map<Integer, String> OUTPUT_ACCIDENTALS_REV;
    private static final Map<String, Integer> INPUT_ACCIDENTALS_REV;
    private static final String ASCENDING_VALUE = "asc";
    private static final String DESCENDING_VALUE = "dsc";
    private static final String SEMITONE = "semitone";
    private static final String DEGREES = "degrees";
    private static final List<String> SINGLE_SEMITONE_LIST = List.of("E", "B");
    private static final List<String> SINGLE_SEMITONE_REVERSE_LIST = List.of("F", "C");
    private static final int FIRST_ARGUMENT = 0;
    private static final int NOTE_ARGUMENT_INDEX = 1;
    private static final int ORDER_ARGUMENT_INDEX = 2;

    static {
        INTERVAL_LIST = new HashMap<>();
        INTERVAL_LIST.put("m2", Map.of(SEMITONE, 1, DEGREES, 2));
        INTERVAL_LIST.put("M2", Map.of(SEMITONE, 2, DEGREES, 2));
        INTERVAL_LIST.put("m3", Map.of(SEMITONE, 3, DEGREES, 3));
        INTERVAL_LIST.put("M3", Map.of(SEMITONE, 4, DEGREES, 3));
        INTERVAL_LIST.put("P4", Map.of(SEMITONE, 5, DEGREES, 4));
        INTERVAL_LIST.put("P5", Map.of(SEMITONE, 7, DEGREES, 5));
        INTERVAL_LIST.put("m6", Map.of(SEMITONE, 8, DEGREES, 6));
        INTERVAL_LIST.put("M6", Map.of(SEMITONE, 9, DEGREES, 6));
        INTERVAL_LIST.put("m7", Map.of(SEMITONE, 10, DEGREES, 7));
        INTERVAL_LIST.put("M7", Map.of(SEMITONE, 11, DEGREES, 7));
        INTERVAL_LIST.put("P8", Map.of(SEMITONE, 12, DEGREES, 8));

        ACCEPTABLE_INPUT_NOTES_LIST = List.of("Cb", "C", "C#", "Db", "D", "D#", "Eb", "E", "E#",
                "Fb", "F", "F#", "Gb", "G", "G#", "Ab", "A", "A#", "Bb", "B", "B#");

        ACCEPTABLE_NOTES_LIST = List.of("Cbb", "Cb", "C", "C#", "C##", "Dbb", "Db", "D", "D#", "D##", "Ebb",
                "Eb", "E", "E#", "E##", "Fbb", "Fb", "F", "F#", "F##", "Gbb", "Gb", "G", "G#", "G##", "Abb", "Ab", "A",
                "A#", "A##", "Bbb", "Bb", "B", "B#", "B##");

        OUTPUT_ACCIDENTALS = Map.of(
                -2, "bb",
                -1, "b",
                0, "",
                1, "#",
                2, "##");
        INPUT_ACCIDENTALS = Map.of(
                "bb", -2,
                "b", -1,
                "", 0,
                "#", 1,
                "##", 2
        );
        OUTPUT_ACCIDENTALS_REV = Map.of(
                2, "bb",
                1, "b",
                0, "",
                -1, "#",
                -2, "##");
        INPUT_ACCIDENTALS_REV = Map.of(
                "bb", 2,
                "b", 1,
                "", 0,
                "#", -1,
                "##", -2
        );
    }

    public static String intervalConstruction(String[] args) {
        validateIntervalConstruction(args);

        String startNoteName = args[NOTE_ARGUMENT_INDEX].substring(0, 1);
        int degree = INTERVAL_LIST.get(args[FIRST_ARGUMENT]).get(DEGREES);

        List<String> currentNoteList = new ArrayList<>(NOTE_LIST);
        Map<String, Integer> currentInputAccidental = INPUT_ACCIDENTALS;
        Map<Integer, String> currentOutputAccidental = OUTPUT_ACCIDENTALS;
        List<String> singleSemitoneList = new ArrayList<>(SINGLE_SEMITONE_LIST);

        if (args.length == 3 && args[ORDER_ARGUMENT_INDEX].equalsIgnoreCase(DESCENDING_VALUE)) {
            Collections.reverse(currentNoteList);
            currentInputAccidental = INPUT_ACCIDENTALS_REV;
            currentOutputAccidental = OUTPUT_ACCIDENTALS_REV;
            singleSemitoneList = SINGLE_SEMITONE_REVERSE_LIST;
        }

        String endNoteName = calculateNoteName(startNoteName, degree, currentNoteList);
        endNoteName += currentOutputAccidental.get(calculateAccidentalName(args, startNoteName, degree, currentNoteList,
                currentInputAccidental, singleSemitoneList));

        return endNoteName;
    }

    public static String intervalIdentification(String[] args) {
        validateIntervalIdentification(args);

        String firstNoteName = args[FIRST_ARGUMENT].substring(0, 1);
        String secondNoteName = args[NOTE_ARGUMENT_INDEX].substring(0, 1);
        int degreeBetweenNotes;
        int semitoneNumber;

        List<String> currentNoteList = new ArrayList<>(NOTE_LIST);
        Map<String, Integer> currentInputAccidentalList = INPUT_ACCIDENTALS;
        List<String> singleSemitoneList = new ArrayList<>(SINGLE_SEMITONE_LIST);

        if (args.length == 3 && args[ORDER_ARGUMENT_INDEX].equalsIgnoreCase(DESCENDING_VALUE)) {
            Collections.reverse(currentNoteList);
            currentInputAccidentalList = INPUT_ACCIDENTALS_REV;
            singleSemitoneList = SINGLE_SEMITONE_REVERSE_LIST;
        }

        degreeBetweenNotes = calculateDegreeBetweenNotes(firstNoteName, secondNoteName, currentNoteList);
        semitoneNumber = calculateSemitonesNumber(args, firstNoteName, degreeBetweenNotes, currentNoteList,
                currentInputAccidentalList, singleSemitoneList);

        Map<String, Integer> intervalDescription = Map.of(SEMITONE, semitoneNumber, DEGREES, degreeBetweenNotes);

        return getIntervalName(intervalDescription);
    }

    private static void validateIntervalConstruction(String[] args) {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Illegal number of elements in input array");
        }

        if (Objects.isNull(INTERVAL_LIST.get(args[FIRST_ARGUMENT]))
                || !ACCEPTABLE_INPUT_NOTES_LIST.contains(args[NOTE_ARGUMENT_INDEX])
                || isIntervalOrderNotValid(args)) {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    private static void validateIntervalIdentification(String[] args) {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Cannot identify the interval");
        }

        if (!ACCEPTABLE_NOTES_LIST.contains(args[FIRST_ARGUMENT])
                || !ACCEPTABLE_NOTES_LIST.contains(args[NOTE_ARGUMENT_INDEX])
                || isIntervalOrderNotValid(args)) {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    private static boolean isIntervalOrderNotValid(String[] args) {
        return args.length == 3
                && !ASCENDING_VALUE.equalsIgnoreCase(args[ORDER_ARGUMENT_INDEX])
                && !DESCENDING_VALUE.equalsIgnoreCase(args[ORDER_ARGUMENT_INDEX]);
    }

    private static String calculateNoteName(String startNoteName, int degree, List<String> currentNoteList) {
        int startNotePosition = currentNoteList.indexOf(startNoteName);
        int endNotePosition = calculateNoteIndex(degree, startNotePosition, currentNoteList.size());
        return currentNoteList.get(endNotePosition);
    }

    public static int calculateNoteIndex(int degree, int start, int size) {
        return (start + degree) % size - 1;
    }

    private static int calculateAccidentalName(String[] args, String startNoteName, int degree,
                                               List<String> currentNoteList, Map<String, Integer> currentInputAccidental,
                                               List<String> singleSemitoneList) {
        int startNotePosition = currentNoteList.indexOf(startNoteName);

        int semitonesNumber = 0;
        int position = startNotePosition;
        for (int i = 0; i < degree - 1; i++, position++) {
            if (position == currentNoteList.size()) {
                position = 0;
            }
            semitonesNumber++;

            if (!singleSemitoneList.contains(currentNoteList.get(position))) {
                semitonesNumber++;
            }
        }

        String accidentalNoteName = args[NOTE_ARGUMENT_INDEX].substring(1);
        int possibleSemitone = INTERVAL_LIST.get(args[FIRST_ARGUMENT]).get(SEMITONE);

        semitonesNumber = semitonesNumber - currentInputAccidental.get(accidentalNoteName);

        return possibleSemitone - semitonesNumber;
    }

    private static int calculateSemitonesNumber(String[] args, String firstNoteName, int degreeBetweenNotes,
                                                List<String> currentNoteList, Map<String, Integer> currentInputAccidentalList,
                                                List<String> singleSemitoneList) {
        int firstNotePosition = currentNoteList.indexOf(firstNoteName);

        int semitoneNumber = 0;
        int position = firstNotePosition;
        for (int i = 0; i < degreeBetweenNotes - 1; i++, position++) {
            if (position == currentNoteList.size()) {
                position = 0;
            }
            semitoneNumber++;
            if (!singleSemitoneList.contains(currentNoteList.get(position))) {
                semitoneNumber++;
            }
        }

        String firstNoteAccidental = args[FIRST_ARGUMENT].substring(1);
        String secondNoteAccidental = args[NOTE_ARGUMENT_INDEX].substring(1);

        semitoneNumber = semitoneNumber - currentInputAccidentalList.get(firstNoteAccidental)
                + currentInputAccidentalList.get(secondNoteAccidental);

        return semitoneNumber;
    }

    private static int calculateDegreeBetweenNotes(String firstNoteName, String secondNoteName, List<String> currentNoteList) {
        int firstNotePosition = currentNoteList.indexOf(firstNoteName);
        int secondNotePosition = currentNoteList.indexOf(secondNoteName);

        return secondNotePosition < firstNotePosition
                ? (currentNoteList.size() - firstNotePosition + secondNotePosition + 1)
                : (secondNotePosition - firstNotePosition + 1);
    }

    private static String getIntervalName(Map<String, Integer> intervalDescription) {
        return INTERVAL_LIST.entrySet().stream()
                .filter(entry -> intervalDescription.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
