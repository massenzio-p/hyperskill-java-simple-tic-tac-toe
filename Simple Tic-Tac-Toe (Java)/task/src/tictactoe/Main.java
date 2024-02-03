package tictactoe;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (var reader = new Scanner(System.in)) {
            var writer = new PrintWriter(System.out);
            TicTacToeGame game = new TicTacToeGame(writer, reader);

            game.play();
        }
    }
}
