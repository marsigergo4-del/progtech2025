package org.example.domain;

import java.util.Scanner;

public class Configuration {
    public int rows;
    public int cols;

    public Configuration(Scanner sc) {
        rows = 10;
        cols = 10;

        while (true) {
            try {
                System.out.print("Sorok száma (5–25) [10]: ");
                String r = sc.nextLine();
                if (!r.isEmpty()) {
                    rows = Integer.parseInt(r);
                }

                System.out.print("Oszlopok száma (5–25) [10]: ");
                String c = sc.nextLine();
                if (!c.isEmpty()) {
                    cols = Integer.parseInt(c);
                }

                if (rows >= 5 && cols >= 5 && rows <= 25 && cols <= 25 && cols <= rows) {
                    break;
                }

                System.out.println("Hibás méret!");
            } catch (NumberFormatException e) {
                System.out.println("Egész szám kell!");
            }
        }
    }

    public Configuration(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
