package org.example.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private int rows;
    private int cols;
    private char[][] grid;

    public Map(int r, int c) {
        rows = r;
        cols = c;
        grid = new char[r][c];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }
    }

    public boolean isEmpty(int r, int c) {
        return grid[r][c] == '.';
    }

    public void place(int r, int c, char mark) {
        grid[r][c] = mark;
    }

    public boolean hasNeighbor(int r, int c) {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                int nr = r + dr;
                int nc = c + dc;
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (grid[nr][nc] != '.') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<int[]> getLegalMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '.' && hasNeighbor(i, j)) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    public boolean checkWin(int r, int c, char who) {
        int[][] dirs = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] d : dirs) {
            int cnt = 1;
            cnt += countDir(r, c, d[0], d[1], who);
            cnt += countDir(r, c, -d[0], -d[1], who);
            if (cnt >= 4) {
                return true;
            }
        }
        return false;
    }

    private int countDir(int r, int c, int dr, int dc, char who) {
        int cnt = 0;
        r += dr;
        c += dc;
        while (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == who) {
            cnt++;
            r += dr;
            c += dc;
        }
        return cnt;
    }

    public void print() {
        System.out.print("   ");
        for (int c = 0; c < cols; c++) {
            System.out.print((char) ('a' + c) + " ");
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            System.out.printf("%2d ", r + 1);
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
