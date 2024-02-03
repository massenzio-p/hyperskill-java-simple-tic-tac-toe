package tictactoe;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame implements Game {

    private static final int BATTLEFIELD_SIZE = 3;

    private char[] battleFieldMatrix;
    private final PrintWriter writer;
    private final Scanner reader;
    private final GameAnalyzer gameAnalyzer;

    public TicTacToeGame(PrintWriter writer, Scanner reader, GameAnalyzer gameAnalyzer) {
        this.writer = writer;
        this.reader = reader;
        this.gameAnalyzer = gameAnalyzer;
    }

    @Override
    public void play() {
        String line = reader.nextLine();
        this.battleFieldMatrix = line.toCharArray();

        printTheField();
        GameState state = this.gameAnalyzer.analyzeGameState(this.battleFieldMatrix);
        String stateMsg = mapStateToMessage(state);
        writer.println(stateMsg);
        writer.flush();
    }

    private String mapStateToMessage(GameState state) {
        return switch (state) {
            case O_WINS -> "O wins";
            case X_WINS -> "X wins";
            case IMPOSSIBLE -> "Impossible";
            case DRAW -> "Draw";
            case GAME_NOT_FINISHED -> "Game not finished";
        };
    }

    private void printTheField() {
        writer.println("---------");
        for (int i = 0, x = 0, y = 0; i < BATTLEFIELD_SIZE * BATTLEFIELD_SIZE; i++) {
            if (x == 0) {
                writer.print("| ");
            }
            writer.print(this.battleFieldMatrix[i]);
            writer.print(' ');
            if (x++ == BATTLEFIELD_SIZE - 1) {
                writer.println("|");
                x = 0;
                y++;
            }
        }
        writer.println("---------");
        writer.flush();
    }
}
