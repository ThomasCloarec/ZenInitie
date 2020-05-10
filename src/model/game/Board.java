package model.game;

public class Board {
    private final Pawn[][] board = {
            {Pawn.RED, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY},
            {Pawn.RED, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.ZEN, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.RED},
            {Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.RED, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLUE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.RED}
    };

    int getFirstDiagonalPawnCount(Position position) {
        int count = 0;
        int diagonalEdge = Math.min(position.getLine(), position.getColumn());
        for (int line = position.getLine() - diagonalEdge, column = position.getColumn() - diagonalEdge; line < this.board.length && column < this.board.length; line++, column++) {
            if (this.board[line][column] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    int getHorizontalPawnCount(Position position) {
        int count = 0;
        for (int column = 0; column < this.board.length; column++) {
            if (this.board[position.getLine()][column] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    int getSecondDiagonalPawnCount(Position position) {
        int count = 0;
        int diagonalEdge = Math.min(this.board.length - position.getLine() - 1, position.getColumn());
        for (int line = position.getLine() + diagonalEdge, column = position.getColumn() - diagonalEdge; line >= 0 && column < this.board.length; line--, column++) {
            if (this.board[line][column] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    int getVerticalPawnCount(Position position) {
        int count = 0;
        for (Pawn[] pawns : this.board) {
            if (pawns[position.getColumn()] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    /**
     * Check if the position is on the board
     *
     * @param position the position to check
     * @return is position on board
     */
    boolean isPositionValid(Position position) {
        boolean lineValid = position.getLine() >= 0 && position.getLine() < this.board.length;
        boolean columnValid = position.getColumn() >= 0 && position.getColumn() < this.board[0].length;
        return lineValid && columnValid;
    }

    Pawn[][] getArray() {
        return this.board.clone();
    }
}
