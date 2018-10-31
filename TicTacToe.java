package tictactoe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author Nazifa Tanzim
 */



public class TicTacToe {
    public static void main(String args[]) {
        TicTacToe game = new TicTacToe('X');
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(game.toString());
            System.out.println(game.getTurn()
                    + ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            scanner.nextLine();
            game.takeTurn(row, column);

        } while (game.getGameState() == TicTacToeEnum.IN_PROGRESS);
        System.out.println(game.getGameState());

    }   
    
    // Declaring private variables
    private int nRows;
    private int nColumns;
    private int numToWin;
    private char[][] grid;
    private char turn;
    private TicTacToeEnum gameState;
    private int nMarks;

    /**
     * Sets variables to default values
     *
     * @param initialTurn
     */
    public TicTacToe(char initialTurn) {
        nRows = 3;
        nColumns = 3;
        numToWin = 3;
        gameState = TicTacToeEnum.IN_PROGRESS;
        nMarks = 0;

        reset(initialTurn);
    }

    /**
     * Sets variables according to user input
     *
     * @param nRows
     * @param nColumns
     * @param numToWin
     * @param initialTurn
     */
    public TicTacToe(int nRows, int nColumns, int numToWin, char initialTurn) {
        if (nRows < 1 || nColumns < 1 || numToWin < 1 || initialTurn < 1) {
            throw new IllegalArgumentException("Invalid Parameters");
        }

        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;

        reset(initialTurn);

    }

    /**
     * Sets board and variables to initial value
     * 
     * @param initialTurn
     */
    public void reset(char initialTurn) {
        turn = initialTurn;
        grid = new char[nRows][nColumns];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    /**
     *
     * @return
     */
    public char getTurn() {
        if (turn == 'X') {
            return 'X';
        }
        return 'O';
    }

    /**
     * Determines what point the game is at
     * 
     * Calls findWinner() to see if there is a winner
     * 
     * @return
     */
    public TicTacToeEnum getGameState() {
        if ((findWinner() == TicTacToeEnum.IN_PROGRESS) && (nMarks == (nRows * nColumns))) {
            return TicTacToeEnum.DRAW;
        }
        return findWinner();
    }

    /**
     *
     * @param player
     * @return
     */
    private TicTacToeEnum charToEnum(char player) {
        if (player == 'O') {
            return TicTacToeEnum.O_WON;
        }
        return TicTacToeEnum.X_WON;
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public TicTacToeEnum takeTurn(int row, int column) {
        grid[row][column] = turn;
        nMarks++;
        
        if (turn == 'X') {
            turn = 'O';
        }
        else{
            turn = 'X';
        }
        
        return getGameState();
    }

    /**
     * Checking each row and column for a match to determine 
     * whether or not there is a winner
     *
     * @return
     */
    private TicTacToeEnum findWinner() {
        // Checking if a row has been completed
        for (int i = 0; i < nRows; i++) {
            // Resetting count for each row
            int matchCount = 0;

            for (int j = 0; j < nColumns; j++) {
                if (grid[i][j] == turn) {
                    matchCount++; // Increasing counter
                    // Checking if row has been completed
                    if (matchCount == nColumns) {
                        // Checking who won
                        return charToEnum(turn);
                    }
                }
            }
        }

        // Checking if a column has been completed
        for (int i = 0; i < nColumns; i++) {
            // Resetting count for each row
            int matchCount = 0;
            for (int j = 0; j < nRows; j++) {
                if (grid[i][j] == turn) {
                    matchCount++; // Increasing counter
                    // Checking if row has been completed
                    if (matchCount == nRows) {
                        // Checking who won
                        return charToEnum(turn);
                    }
                }
            }
        }
        return TicTacToeEnum.IN_PROGRESS;
    }

    /**
     * Printing board
     *
     * @return
     */
    @Override
    public String toString() {
        String s = new String();

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                s += grid[i][j] + " | ";
            }

            s += "\n";
        }
        return s;
    }
}
