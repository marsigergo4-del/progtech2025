package org.example.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {
    private String fileName;
    private Map<String, int[]> scores = new HashMap<>();

    public ScoreBoard(String fileName) {
        this.fileName = fileName;
    }

    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                scores.put(p[0], new int[]{Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3])});
            }
        } catch (IOException ignored) {
          
        }
    }

    public void record(String name, int win, int loss, int draw) {
        scores.putIfAbsent(name, new int[]{0, 0, 0});
        int[] s = scores.get(name);
        s[0] += win;
        s[1] += loss;
        s[2] += draw;
    }

    public void print() {
        System.out.println("=== SCOREBOARD ===");
        for (var e : scores.entrySet()) {
            int[] s = e.getValue();
            System.out.printf("%s → Győzelem:%d Vereség:%d Döntetlen:%d%n", e.getKey(), s[0], s[1], s[2]);
        }
    }

    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (var e : scores.entrySet()) {
                int[] s = e.getValue();
                pw.println(e.getKey() + ";" + s[0] + ";" + s[1] + ";" + s[2]);
            }
        } catch (IOException e) {
            System.out.println("Nem sikerült menteni a scoreboardot!");
        }
    }
    Map<String, int[]> getScoresForTest() {
        return scores;
    }
}
