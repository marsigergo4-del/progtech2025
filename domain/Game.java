package org.example.domain;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {

    private static final Logger LOG = LoggerFactory.getLogger(Game.class);

    private Map map;
    private Player human;
    private ComputerPlayer computer;
    private ScoreBoard scoreBoard;
    private Scanner sc;

    // Konstruktor MapInit kompatibilis
    public Game(Map map, Player human, ComputerPlayer computer, ScoreBoard scoreBoard, Scanner sc) {
        this.map = map;
        this.human = human;
        this.computer = computer;
        this.scoreBoard = scoreBoard;
        this.sc = sc;

        LOG.info("Új játék létrehozva: játékos={}, tábla={}x{}", human.getName(), map.getRows(), map.getCols());
    }

    // Játék indítása
    public void start() {
        boolean humanTurn = false;
        LOG.info("Játék elindult");
        map.print();

        while (true) {
            boolean continueGame = humanTurn ? humanMove() : computerMove();
            if (!continueGame) {
                LOG.info("Játék vége");
                break;
            }
            humanTurn = !humanTurn;
        }
    }

    // Emberi lépés
    private boolean humanMove() {
        while (true) {
            System.out.print("Lépés (pl. a1): ");
            String input = sc.nextLine().trim().toLowerCase();
            LOG.debug("Felhasználói input: {}", input);

            if (input.length() < 2) {
                System.out.println("Hibás formátum!");
                LOG.warn("Hibás formátumú input");
                continue;
            }

            int col = input.charAt(0) - 'a';
            int row;
            try {
                row = Integer.parseInt(input.substring(1)) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Hibás sor!");
                LOG.warn("Nem szám a sor: {}", input);
                continue;
            }

            if (row < 0 || row >= map.getRows() || col < 0 || col >= map.getCols()) {
                System.out.println("Táblán kívül léptél!");
                LOG.warn("Táblán kívüli lépés: row={}, col={}", row, col);
                continue;
            }

            if (!map.isEmpty(row, col)) {
                System.out.println("A mező foglalt!");
                LOG.warn("Foglalt mező: row={}, col={}", row, col);
                continue;
            }

            if (!map.hasNeighbor(row, col)) {
                System.out.println("Nem érintkezik más jellel!");
                LOG.warn("Szomszéd nélküli lépés: row={}, col={}", row, col);
                continue;
            }

            map.place(row, col, human.getMark());
            LOG.info("Emberi lépés: row={}, col={}", row, col);
            map.print();

            if (map.checkWin(row, col, human.getMark())) {
                System.out.println("🎉 Nyertél!");
                LOG.info("Emberi játékos nyert");
                scoreBoard.record(human.getName(), 1, 0, 0);
                scoreBoard.print();
                return false; // játék vége
            }

            return true; // sikeres lépés
        }
    }

    // Gép lépés
    private boolean computerMove() {
        if (map.getLegalMoves().isEmpty()) {
            System.out.println("Nincs több lépés. Döntetlen!");
            LOG.info("Döntetlen – nincs több lépés");
            scoreBoard.record(human.getName(), 0, 0, 1);
            scoreBoard.print();
            return false;
        }

        int[] move = computer.chooseMove(map);
        map.place(move[0], move[1], computer.getMark());
        LOG.info("Gép lépett: row={}, col={}", move[0], move[1]);
        System.out.println("A gép lépett: " + (char) ('a' + move[1]) + (move[0] + 1));
        map.print();

        if (map.checkWin(move[0], move[1], computer.getMark())) {
            System.out.println("😞 A gép nyert!");
            LOG.info("Gép nyert");
            scoreBoard.record(human.getName(), 0, 1, 0);
            scoreBoard.print();
            return false; // játék vége
        }

        return true; // sikeres lépés
    }
}
