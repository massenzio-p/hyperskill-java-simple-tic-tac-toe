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
//        initBattlefield();
    }

    private void initBattlefield() {
        Arrays.fill(battleFieldMatrix[0], '_');
        Arrays.fill(battleFieldMatrix[1], '_');
        Arrays.fill(battleFieldMatrix[2], '_');
    }

    @Override
    public void play() {
        String line = reader.nextLine();
        for (int chIdx = 0, i = 0,j = 0; chIdx < BATTLEFIELD_SIZE * BATTLEFIELD_SIZE; chIdx++) {
            this.battleFieldMatrix[i][j] = line.charAt(chIdx);
            if (j++ == 2) {
                i++;
                j = 0;
            }
        }
        printTheField();
    }

    private void printTheField() {
        writer.println("---------");
        for (int y = 0; y < BATTLEFIELD_SIZE; y++) {
            writer.print("| ");
            for (int x = 0; x < BATTLEFIELD_SIZE; x++) {
                writer.print(battleFieldMatrix[y][x]);
                writer.print(' ');
            }
            writer.println("|");
        }
        writer.println("---------");
        writer.flush();
    }
}
