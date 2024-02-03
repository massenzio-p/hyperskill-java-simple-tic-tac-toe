package tictactoe;

import java.util.Set;

public class GameAnalyzerImpl implements GameAnalyzer {

    private int numberOfX = 0;
    private int numberOfO = 0;
    private char[] gameSnapshot;


    @Override
    public GameState analyzeGameState(char[] gameSnapshot) {
        this.gameSnapshot = gameSnapshot;
        calculateMoves();

        if (checkIfImpossible(gameSnapshot)) {
            return GameState.IMPOSSIBLE;
        }

        boolean xWins = determineIfOptionAWinner('X');
        boolean oWins = determineIfOptionAWinner('O');

        if (xWins && oWins) {
            return GameState.IMPOSSIBLE;
        } else if (xWins) {
            return GameState.X_WINS;
        } else if (oWins) {
            return GameState.O_WINS;
        }

        if (numberOfX + numberOfO == gameSnapshot.length) {
            return GameState.DRAW;
        }
        return GameState.GAME_NOT_FINISHED;
    }

    private boolean determineIfOptionAWinner(char option) {
        boolean winner = false;
        // column
        int battleSize = 3;
        for (int column = 0; column < battleSize; column++) {
            for (int i = column; i < gameSnapshot.length; i += battleSize) {
                if (gameSnapshot[i] == option) {
                    winner = true;
                } else {
                    winner = false;
                    break;
                }
            }
            if (winner) return true;
        }
        // rows
        for (int row = 0; row < battleSize; row++) {
            for (int i = row; i < row + battleSize; i++) {
                if (gameSnapshot[i] == option) {
                    winner = true;
                } else {
                    winner = false;
                    break;
                }
            }
            if (winner) return true;
        }
        // diagonal 1 "\"
        for (int col = 0; col < gameSnapshot.length; col += battleSize + battleSize / 2) {
            if (gameSnapshot[col] == option) {
                winner = true;
            } else {
                winner = false;
                break;
            }
        }
        if (winner) return true;
        // diagonal 2 "/"
        for (int idx = gameSnapshot.length - battleSize; idx > 0; idx -= battleSize - battleSize / 2) {
            if (gameSnapshot[idx] == option) {
                winner = true;
            } else {
                winner = false;
                break;
            }
        }
        if (winner) return true;
        return false;
    }


    private void calculateMoves() {
        this.numberOfX = 0;
        this.numberOfO = 0;
        for (var position : gameSnapshot) {
            if (position == 'X') {
                numberOfX++;
            } else if (position == 'O') {
                numberOfO++;
            }
        }
    }

    private boolean checkIfImpossible(char[] gameSnapshot) {
        return Math.abs(numberOfX - numberOfO) > 1;
    }
}
