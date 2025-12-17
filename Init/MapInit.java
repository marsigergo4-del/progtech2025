package org.example.init;

import org.example.domain.Configuration;
import org.example.domain.Map;
import org.example.domain.Player;

public final class MapInit {

    private MapInit() {
    }

    public static Map createInitialMap(Configuration cfg, Player firstPlayer) {
        Map map = new Map(cfg.rows, cfg.cols);

        int r = (cfg.rows - 1) / 2;
        int c = (cfg.cols - 1) / 2;
        map.place(r, c, firstPlayer.getMark());
        return map;
    }
}
