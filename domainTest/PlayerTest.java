package org.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerCreation() {
        Player player = new Player("Alice", 'X');

        assertEquals("Alice", player.getName(), "A játékos neve helyesen legyen beállítva");
        assertEquals('X', player.getMark(), "A játékos jele helyesen legyen beállítva");
    }

    @Test
    void testAnotherPlayer() {
        Player player = new Player("Bob", 'O');

        assertEquals("Bob", player.getName());
        assertEquals('O', player.getMark());
    }
}
