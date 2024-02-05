package tictactoe;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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
        acceptPlayerMove(reader, writer);
        printTheField();

//        GameState state = this.gameAnalyzer.analyzeGameState(this.battleFieldMatrix);
//        String stateMsg = mapStateToMessage(state);
//        writer.println(stateMsg);
//        writer.flush();
    }

    private void acceptPlayerMove(Scanner reader, PrintWriter writer) {
        StringTokenizer tokenizer;
        int[] move;
        while (true) {
            try {
                tokenizer = new StringTokenizer(reader.nextLine(), " ");
                move = new int[] {
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())
                };
            } catch (NumberFormatException e) {
                writer.println("You should enter numbers!");
                writer.flush();
                continue;
            }
            boolean outOfRange = false;
            for (var coord : move) {
                if (coord < 1 || coord > 3) {
                    outOfRange = true;
                    break;
                }
            }
            if (outOfRange) {
                writer.println("Coordinates should be from 1 to 3!");
                writer.flush();
                continue;
            }
            int actualIndex = (move[0] - 1) * BATTLEFIELD_SIZE + move[1] - 1;
            if (this.battleFieldMatrix[actualIndex] != '_') {
                writer.println("This cell is occupied! Choose another one!");
                writer.flush();
                continue;
            } else {
                this.battleFieldMatrix[actualIndex] = 'X';
            }
            return;
        }
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
            if (this.battleFieldMatrix[i] == '_') {
                writer.print(" ");
            } else {
                writer.print(this.battleFieldMatrix[i]);
            }
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
