package model.game.board;

import model.game.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Board.
 */
public class Board {
    /**
     * The Board.
     */
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

    /**
     * Gets pawn positions around.
     *
     * @param pawnsToGetAround the pawns to get around
     * @return the pawn positions around
     */
    public Map<Position, Position[]> getPawnPositionsAround(Pawn... pawnsToGetAround) {
        Map<Position, Position[]> pawnPositionsAround = new HashMap<>();

        for (Position position : this.getPawnPositions(pawnsToGetAround)) {
            // 8 columns to check if there is one connected around
            Position[] testPositions = {
                    new Position(position.getLine() - 1, position.getColumn()),
                    new Position(position.getLine() + 1, position.getColumn()),
                    new Position(position.getLine(), position.getColumn() - 1),
                    new Position(position.getLine(), position.getColumn() + 1),
                    new Position(position.getLine() - 1, position.getColumn() - 1),
                    new Position(position.getLine() - 1, position.getColumn() + 1),
                    new Position(position.getLine() + 1, position.getColumn() - 1),
                    new Position(position.getLine() + 1, position.getColumn() + 1)
            };

            // Filter valid positions by using a List<Position>
            List<Position> positionList = new ArrayList<>();
            for (Position testPosition : testPositions) {
                if (this.isPositionValid(testPosition)) {
                    positionList.add(testPosition);
                }
            }

            // Convert back to Position[]
            Position[] positions = new Position[positionList.size()];
            for (int i = 0; i < positions.length; i++) {
                positions[i] = positionList.get(i);
            }

            pawnPositionsAround.put(position, positions);
        }

        return pawnPositionsAround;
    }

    /**
     * Generate positions from position [ ].
     *
     * @param position the position
     * @return the position [ ]
     */
    public Position[] generatePositionsFrom(Position position) {
        Position[] positions = new Position[8];

        int verticalPawnCount = this.getVerticalPawnCount(position);
        positions[0] = new Position(position.getLine() - verticalPawnCount, position.getColumn()); // top
        positions[1] = new Position(position.getLine() + verticalPawnCount, position.getColumn()); // bottom

        int horizontalPawnCount = this.getHorizontalPawnCount(position);
        positions[2] = new Position(position.getLine(), position.getColumn() - horizontalPawnCount); // left
        positions[3] = new Position(position.getLine(), position.getColumn() + horizontalPawnCount); // right

        int diagonal1PawnCount = this.getFirstDiagonalPawnCount(position);
        positions[4] = new Position(position.getLine() - diagonal1PawnCount, position.getColumn() - diagonal1PawnCount); // top left
        positions[5] = new Position(position.getLine() - diagonal1PawnCount, position.getColumn() + diagonal1PawnCount); // top right

        int diagonal2PawnCount = this.getSecondDiagonalPawnCount(position);
        positions[6] = new Position(position.getLine() + diagonal2PawnCount, position.getColumn() - diagonal2PawnCount); // bottom left
        positions[7] = new Position(position.getLine() + diagonal2PawnCount, position.getColumn() + diagonal2PawnCount); // bottom right
        return positions;
    }

    /**
     * Check if the position is on the board
     *
     * @param position the position to check
     * @return is position on board
     */
    public boolean isPositionValid(Position position) {
        boolean lineValid = position.getLine() >= 0 && position.getLine() < this.board.length;
        boolean columnValid = position.getColumn() >= 0 && position.getColumn() < this.board[0].length;
        return lineValid && columnValid;
    }

    /**
     * Gets pawn count.
     *
     * @param pawnsToCount the pawns to count
     * @return the pawn count
     */
    int getPawnCount(Pawn... pawnsToCount) {
        int count = 0;
        for (Pawn[] pawns : this.board) {
            for (Pawn pawn : pawns) {
                for (Pawn pawnToCount : pawnsToCount) {
                    if (pawn == pawnToCount) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /**
     * Get pawn positions position [ ].
     *
     * @param pawnsToGet the pawns to get
     * @return the position [ ]
     */
    Position[] getPawnPositions(Pawn... pawnsToGet) {
        Position[] positions = new Position[this.getPawnCount(pawnsToGet)];

        int positionIndex = 0;
        for (int line = 0; line < this.board.length; line++) {
            for (int column = 0; column < this.board[0].length; column++) {
                for (Pawn pawnToGet : pawnsToGet) {
                    if (this.board[line][column] == pawnToGet) {
                        positions[positionIndex] = new Position(line, column);
                        positionIndex++;
                    }
                }
            }
        }

        return positions;
    }

    /**
     * Gets pawn.
     *
     * @param position the position
     * @return the pawn
     */
    Pawn getPawn(Position position) {
        return this.board[position.getLine()][position.getColumn()];
    }

    /**
     * Gets first diagonal pawn count.
     *
     * @param position the position
     * @return the first diagonal pawn count
     */
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

    /**
     * Gets horizontal pawn count.
     *
     * @param position the position
     * @return the horizontal pawn count
     */
    int getHorizontalPawnCount(Position position) {
        int count = 0;
        for (int column = 0; column < this.board.length; column++) {
            if (this.board[position.getLine()][column] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    /**
     * Gets second diagonal pawn count.
     *
     * @param position the position
     * @return the second diagonal pawn count
     */
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

    /**
     * Gets vertical pawn count.
     *
     * @param position the position
     * @return the vertical pawn count
     */
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
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Board{" +
                "board=" + Arrays.toString(this.board) +
                '}';
    }

    /**
     * Get array pawn [ ] [ ].
     *
     * @return the pawn [ ] [ ]
     */
    public Pawn[][] getArray() {
        return this.board.clone();
    }
}
