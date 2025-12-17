package org.example.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @Test
    void testChooseMoveReturnsLegalMove() {
        // Készítünk egy 3x3-as map-et
        Map map = new Map(3, 3);
        map.place(1, 1, 'X'); // középső mező foglalt

        ComputerPlayer cpu = new ComputerPlayer("CPU", 'O');

        // gép választ egy lépést
        int[] move = cpu.chooseMove(map);

        List<int[]> legalMoves = map.getLegalMoves();

        boolean found = legalMoves.stream()
                .anyMatch(m -> m[0] == move[0] && m[1] == move[1]);

        assertTrue(found, "A ComputerPlayer csak jogszerű mezőt választhat");
    }
}
