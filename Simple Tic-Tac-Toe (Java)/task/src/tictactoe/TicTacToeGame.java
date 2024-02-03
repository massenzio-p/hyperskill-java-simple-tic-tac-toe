package tictactoe;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame implements Game {

    private static final int BATTLEFIELD_SIZE = 3;

    private final char[][] battleFieldMatrix = new char[BATTLEFIELD_SIZE][BATTLEFIELD_SIZE];
    private final PrintWriter writer;
    private final Scanner reader;

    public TicTacToeGame(PrintWriter writer, Scanner reader) {
        this.writer = writer;
        this.reader = reader;
        initBattlefield();
    }

    private void initBattlefield() {
        Arrays.fill(battleFieldMatrix[0], 'X');
        Arrays.fill(battleFieldMatrix[1], 'O');
        Arrays.fill(battleFieldMatrix[2], 'X');
    }

    @Override
    public void play() {
        printTheField();
    }

    private void printTheField() {
        for (int y = 0; y < BATTLEFIELD_SIZE; y++) {
            for (int x = 0; x < BATTLEFIELD_SIZE; x++) {
                writer.print(battleFieldMatrix[y][x]);
                if (x < BATTLEFIELD_SIZE - 1) {
                    writer.print(' ');
                }
            }
            writer.println();
        }
    }
}
