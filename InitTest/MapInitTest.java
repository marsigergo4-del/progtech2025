package org.example.Init;

import org.example.domain.Map;
import org.example.domain.Player;
import org.example.domain.Configuration;
import org.example.init.MapInit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapInitTest {

    @Test
    void testCreateInitialMapPlacesFirstPlayerInCenter() {
        // Gyors, kicsi 3x3-as tábla
        Configuration cfg = new Configuration(3, 3);
        Player player = new Player("Alice", 'X');

        Map map = MapInit.createInitialMap(cfg, player);

        int centerRow = (cfg.getRows() - 1) / 2;
        int centerCol = (cfg.getCols() - 1) / 2;

        assertFalse(map.isEmpty(centerRow, centerCol), "A középső mező nem lehet üres");

        assertEquals('X', getMarkAt(map, centerRow, centerCol), "A középső mezőn a megfelelő jelnek kell lennie");
    }

    private char getMarkAt(Map map, int row, int col) {
        try {
            java.lang.reflect.Field gridField = Map.class.getDeclaredField("grid");
            gridField.setAccessible(true);
            char[][] grid = (char[][]) gridField.get(map);
            return grid[row][col];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
