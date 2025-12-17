package org.example.init;

import java.util.Scanner;

import org.example.domain.ComputerPlayer;
import org.example.domain.Player;

public final class PlayerInit {

    private PlayerInit() {
    }

    public static Player createHumanPlayer(Scanner sc) {
        System.out.print("Add meg a neved: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            name = "Játékos";
        }
        return new Player(name, 'X');
    }

    public static ComputerPlayer createComputerPlayer() {
        return new ComputerPlayer("Gép", 'O');
    }
}
