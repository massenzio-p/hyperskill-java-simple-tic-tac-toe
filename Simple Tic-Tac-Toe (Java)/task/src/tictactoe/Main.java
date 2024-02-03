package tictactoe;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (var writer = new PrintWriter(System.out);
             var reader = new Scanner(System.in)) {
            TicTacToeGame game = new TicTacToeGame(writer, reader);

            game.play();
        }
    }
}
