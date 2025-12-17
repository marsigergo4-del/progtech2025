package org.example.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void testInitialMapIsEmpty() {
        Map map = new Map(5, 5);

        for (int r = 0; r < map.getRows(); r++) {
            for (int c = 0; c < map.getCols(); c++) {
                assertTrue(map.isEmpty(r, c), "Minden mezőnek üresnek kell lennie a kezdéskor");
            }
        }
    }

    @Test
    void testPlaceAndIsEmpty() {
        Map map = new Map(3, 3);
        map.place(1, 1, 'X');

        assertFalse(map.isEmpty(1, 1), "A lerakott mező nem lehet üres");
        assertTrue(map.isEmpty(0, 0), "A nem érintett mező üres marad");
    }

    @Test
    void testHasNeighborBasic() {
        Map map = new Map(3, 3);
        map.place(1, 1, 'X');

        assertTrue(map.hasNeighbor(0, 0));
        assertTrue(map.hasNeighbor(0, 1));
        assertTrue(map.hasNeighbor(2, 2));

        assertFalse(map.hasNeighbor(1, 1));
    }

    @Test
    void testHasNeighborEdges() {
        Map map = new Map(3,3);
        map.place(1,1,'X');

        // Közvetlen szomszédoknak true-nak kell lenniük
        assertTrue(map.hasNeighbor(0,1), "Felső középső szomszéd");
        assertTrue(map.hasNeighbor(1,0), "Bal középső szomszéd");
        assertTrue(map.hasNeighbor(1,2), "Jobb középső szomszéd");
        assertTrue(map.hasNeighbor(2,1), "Alsó középső szomszéd");

        // Sarok mező üres, de van szomszéd → hasNeighbor true
        assertTrue(map.hasNeighbor(0,0), "Felső bal saroknak van szomszédja");
        assertTrue(map.hasNeighbor(0,2), "Felső jobb saroknak van szomszédja");
        assertTrue(map.hasNeighbor(2,0), "Alsó bal saroknak van szomszédja");
        assertTrue(map.hasNeighbor(2,2), "Alsó jobb saroknak van szomszédja");
    }


    @Test
    void testCheckWinHorizontal() {
        Map map = new Map(5, 5);
        map.place(0,0,'X');
        map.place(0,1,'X');
        map.place(0,2,'X');
        map.place(0,3,'X');

        assertTrue(map.checkWin(0,3,'X'));
    }

    @Test
    void testCheckWinVertical() {
        Map map = new Map(5, 5);
        map.place(0,0,'O');
        map.place(1,0,'O');
        map.place(2,0,'O');
        map.place(3,0,'O');

        assertTrue(map.checkWin(3,0,'O'));
    }

    @Test
    void testCheckWinDiagonal() {
        Map map = new Map(5, 5);
        map.place(0,0,'X');
        map.place(1,1,'X');
        map.place(2,2,'X');
        map.place(3,3,'X');

        assertTrue(map.checkWin(3,3,'X'));
    }

    @Test
    void testCheckWinAntiDiagonal() {
        Map map = new Map(5,5);
        map.place(0,3,'O');
        map.place(1,2,'O');
        map.place(2,1,'O');
        map.place(3,0,'O');

        assertTrue(map.checkWin(3,0,'O'));
    }

    @Test
    void testCheckWinFalse() {
        Map map = new Map(4,4);
        map.place(0,0,'X');
        map.place(0,1,'X');
        map.place(0,2,'O');
        assertFalse(map.checkWin(0,1,'X'));
    }

    @Test
    void testGetLegalMovesWithNeighbors() {
        Map map = new Map(3,3);
        map.place(1,1,'X');

        List<int[]> legal = map.getLegalMoves();

        for (int[] move : legal) {
            assertTrue(map.hasNeighbor(move[0], move[1]));
        }
    }

    @Test
    void testGetLegalMovesEmptyMap() {
        Map map = new Map(3,3);
        List<int[]> legal = map.getLegalMoves();
        assertTrue(legal.isEmpty(), "Üres mapnél nincs jogszerű lépés");
    }

    @Test
    void testPrint() {
        Map map = new Map(2,2);
        map.print(); // coverage miatt
    }
}
