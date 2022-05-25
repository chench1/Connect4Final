package main.java;
/*
Author - Charles Chen
Date - 5/24/2022
Purpose - 
*/

public class BoardLogic {
    public static final int WIDTH = 125;
    public static final int HEIGHT = 100;
    public int turn;
    private final BoardGUI board;

    public BoardLogic(BoardGUI b) {
        board = b;
    }

    private int[] findOpenSpace(int column) {
        for (int i = 5; i >= 0; i--) {
            if (board.getSlotsIcons()[i][column - 1].getIcon().equals(board.getBlank())) {
                return new int[]{column - 1, i};
            }
        }
        return new int[]{-1, -1};
    }

    public void placeToken(int button) throws InvalidMoveException {
        int[] move = findOpenSpace(button);

        if (move[0] == -1) {
            throw new InvalidMoveException("Invalid move");
        }

        try {
            board.playMove(move[0], move[1], turn);
        } catch (Exception e) {
            System.out.println("Missing file");
        }

        if (checkForWinner()) {
            System.out.println("cool");
        }
    }

//    public int[][] getBoardSymbols() {
//
//        //for (int y = 0; y < 5;)
//    }

    public boolean checkForWinner() {
        //return horizontal();
        return true;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int val) {
        turn = val;
    }
}
