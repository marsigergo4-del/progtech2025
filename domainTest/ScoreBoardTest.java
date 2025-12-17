package org.example.domain;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    void testRecordAndRetrieveScores() {
        ScoreBoard sb = new ScoreBoard("test_scores.txt");
        sb.record("Alice", 1, 0, 0);
        sb.record("Alice", 0, 1, 1);
      
        int[] scores = sb.getScoresForTest().get("Alice");
        assertEquals(1, scores[0], "Győzelem száma helyes legyen");
        assertEquals(1, scores[1], "Vereség száma helyes legyen");
        assertEquals(1, scores[2], "Döntetlen száma helyes legyen");
    }

    @Test
    void testSaveAndLoad() throws IOException {
        // Ideiglenes fájl létrehozása
        File temp = File.createTempFile("scoreboard", ".txt");
        temp.deleteOnExit();

        // Első ScoreBoard, rögzítünk egy játékost és mentünk
        ScoreBoard sb = new ScoreBoard(temp.getAbsolutePath());
        sb.record("Bob", 2, 1, 0);
        sb.save();

        // Második ScoreBoard, betöltjük a fájlt
        ScoreBoard sb2 = new ScoreBoard(temp.getAbsolutePath());
        sb2.load();

        int[] scores = sb2.getScoresForTest().get("Bob");
        assertEquals(2, scores[0], "Győzelem száma helyes legyen");
        assertEquals(1, scores[1], "Vereség száma helyes legyen");
        assertEquals(0, scores[2], "Döntetlen száma helyes legyen");
    }

    @Test
    void testPrintDoesNotThrow() {
        ScoreBoard sb = new ScoreBoard("dummy.txt");
        sb.record("Charlie", 0, 0, 1);

        // Ellenőrizzük, hogy a print() metódus nem dob kivételt
        assertDoesNotThrow(sb::print, "A print() nem dobhat kivételt");
    }
}
