package model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    Map<Position, Position[]> getPawnPositionsAround(Pawn... pawnsToGetAround) {
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

    Pawn getPawn(Position position) {
        return this.board[position.getLine()][position.getColumn()];
    }

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

    Position[] generatePositionsFrom(Position position) {
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
    boolean isPositionValid(Position position) {
        boolean lineValid = position.getLine() >= 0 && position.getLine() < this.board.length;
        boolean columnValid = position.getColumn() >= 0 && position.getColumn() < this.board[0].length;
        return lineValid && columnValid;
    }

    Pawn[][] getArray() {
        return this.board.clone();
    }
}
