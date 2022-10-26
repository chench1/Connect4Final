public class BoardLogic {
    public static final int WIDTH = 125;
    public static final int HEIGHT = 100;
    private boolean useAI = true;
    private int turn;
    private final BoardGUI board;
    private boolean runningGame = true;

    public BoardLogic(BoardGUI b) {
        board = b;
        turn = 1;
    }

    private int[] findOpenSpace(int column) {
        for (int y = 5; y >= 0; y--) {
            if (board.getSlotSymbol(column - 1, y) == 0) {
                return new int[] { column - 1, y };
            }
        }
        return new int[] { -1, -1 };
    }

    public void placeToken(int button) throws InvalidMoveException {
        if (!runningGame)
            return;

        int[] move = findOpenSpace(button);

        if (move[0] == -1) {
            throw new InvalidMoveException("Invalid move");
        }

        try {
            board.playMove(move[0], move[1], turn);
            switchTurns();
        } catch (Exception e) {
            System.out.println("Error running game");
        }

        if (checkForWinner(1)) {
            System.out.println("PLAYER HAS WON");
            winner(1);
            return;
        }

        if (useAI) {
            int[][] boardSymbols = getBoardSymbols();
            int[] AIMove = MiniMax.generateBestMove(boardSymbols, turn);

            if (AIMove[0] == -1) {
                winner(1);
                return;
            }
            try {
                board.playMove(AIMove[0], AIMove[1], turn);
                switchTurns();
                if (checkForWinner(-1)) {
                    System.out.println("AI HAS WON");
                    winner(-1);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error making AI move " + e.getMessage());
            }
        }
    }

    public int[][] getBoardSymbols() {
        int[][] boardSymbols = new int[6][7];

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 7; x++) {
                boardSymbols[y][x] = board.getSlotSymbol(x, y);
            }
        }

        return boardSymbols;
    }

    public void winner(int player) {
        runningGame = false;
        board.showWinner(player);
    }

    public boolean checkForWinner(int player) {
        int[][] boardSymbols = getBoardSymbols();

        // horizontal
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                int playerInLine = 0;

                for (int xChange = x; xChange < x + 4; xChange++) {
                    if (boardSymbols[y][xChange] == player)
                        playerInLine++;
                }

                if (playerInLine == 4) {
                    return true;
                }
            }
        }

        // vertical
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 3; y++) {
                int playerInLine = 0;

                for (int yChange = y; yChange < y + 4; yChange++) {
                    if (boardSymbols[yChange][x] == player)
                        playerInLine++;
                }

                if (playerInLine == 4) {
                    return true;
                }
            }
        }

        // diagonal top left to bottom right
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                int playerInLine = 0;

                for (int change = 0; change < 4; change++) {
                    if (boardSymbols[y + change][x + change] == player)
                        playerInLine++;
                }

                if (playerInLine == 4) {
                    return true;
                }
            }
        }

        // diagonal top right to bottom left
        for (int x = 3; x < 7; x++) {
            for (int y = 0; y < 3; y++) {
                int playerInLine = 0;

                for (int change = 0; change < 4; change++) {
                    if (boardSymbols[y + change][x - change] == player)
                        playerInLine++;
                }

                if (playerInLine == 4) {
                    return true;
                }
            }
        }

        return false;
    }

    public int getTurn() {
        return turn;
    }

    public void switchTurns() {
        turn = -turn;
    }
}
