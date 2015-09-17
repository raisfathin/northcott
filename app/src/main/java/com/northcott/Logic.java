package com.northcott;

import java.util.Random;

public class Logic {
    private int rowCount;
    private int colCount;
    private int position[][];

    public Logic(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.position = new int[2][rowCount];
        Random rnd = new Random(System.currentTimeMillis());
        for (int player = 0; player < 2; ++player) {
            for (int i = 0; i < rowCount; ++i) {
                position[player][i] = rnd.nextInt(colCount);
                if (player == 1) {
                   while (position[player][i] == position[0][i]) {
                       position[player][i] = rnd.nextInt(colCount);
                   }
                }
            }
        }
    }

    public boolean move(int player, int rowIndex, int moveToColIndex) {
        if (position[player][rowIndex] == moveToColIndex) {
            return false;
        }
        if (position[player][rowIndex] < position[1 - player][rowIndex]
                && position[1 - player][rowIndex] <= moveToColIndex) {
            return false;
        }
        if (moveToColIndex <= position[1 - player][rowIndex]
                && position[1 - player][rowIndex] < position[player][rowIndex]) {
            return false;
        }
        position[player][rowIndex] = moveToColIndex;
        return true;
    }

    public int[][] getPosition() {
        return position;
    }
}
