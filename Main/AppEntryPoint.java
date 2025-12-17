package org.example;

import java.util.Scanner;

import org.example.domain.ComputerPlayer;
import org.example.domain.Configuration;
import org.example.domain.Game;
import org.example.domain.Map;
import org.example.domain.Player;
import org.example.domain.ScoreBoard;
import org.example.init.MapInit;
import org.example.init.PlayerInit;

@SuppressWarnings({"PMD.SystemPrintln"})
public class AppEntryPoint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Amőba játék ===");

        // Játékosok létrehozása PlayerInit-tel
        Player human = PlayerInit.createHumanPlayer(sc);
        ComputerPlayer computer = PlayerInit.createComputerPlayer();

        Configuration config = new Configuration(sc);

        // Scoreboard
        ScoreBoard scoreBoard = new ScoreBoard("scoreboard.txt");
        scoreBoard.load();

        // Pálya létrehozása
        Map map = MapInit.createInitialMap(config, human);

        // Játék indítása
        Game game = new Game(map, human, computer, scoreBoard, sc);
        game.start();

        scoreBoard.save();
        sc.close();
    }
}
