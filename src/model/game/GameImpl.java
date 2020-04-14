package model.game;

import java.util.ArrayList;

public class GameImpl extends Game {

    private boolean firstPlayerInTeamTurn = true;
    private ArrayList<String> playersBlack;
    private ArrayList<String> playersWhite;
    private Position selectedPawn;

    public GameImpl(boolean aiMode, boolean duoMode, boolean onlineMode) {
        super(aiMode, duoMode, onlineMode);
    }

    @Override
    public boolean isMoveAllowed(Position move) {
        boolean allowed = false;

        int i = 0;
        while (i < this.allowedMoves.size() && !allowed) {
            if (move.getLine() == this.allowedMoves.get(i).getLine() && move.getColumn() == this.allowedMoves.get(i).getColumn()) {
                allowed = true;
            }
            i++;
        }

        return allowed;
    }

    @Override
    public boolean isPawnSelectable(Position position) {
        Pawn pawn = this.board[position.getLine()][position.getColumn()];
        return pawn == this.getPawnActualTurn() || (pawn == Pawn.ZEN);
    }

    @Override
    public void moveSelectedPawn(Position position) {
        this.board[position.getLine()][position.getColumn()] = this.board[this.selectedPawn.getLine()][this.selectedPawn.getColumn()];
        this.board[this.selectedPawn.getLine()][this.selectedPawn.getColumn()] = Pawn.EMPTY;

        this.whiteTeamTurn = (this.duoMode && this.firstPlayerInTeamTurn) == this.whiteTeamTurn; // logic, isn't it ?
        this.firstPlayerInTeamTurn = this.duoMode ^ this.firstPlayerInTeamTurn; // ^ = xor (interesting no ?)
        this.notifyPawnMoved();
    }

    private Position[] generatePositionsFrom(Position position) {
        Position[] positions = new Position[8];

        int verticalPawnCount = this.getVerticalPawnCount(position);
        positions[0] = new Position(position.getLine() - verticalPawnCount, position.getColumn()); // top
        positions[1] = new Position(position.getLine() + verticalPawnCount, position.getColumn()); // bottom

        int horizontalPawnCount = this.getHorizontalPawnCount(position);
        positions[2] = new Position(position.getLine(), position.getColumn() - horizontalPawnCount); // left
        positions[3] = new Position(position.getLine(), position.getColumn() + horizontalPawnCount); // right

        int firstDiagonalPawnCount = this.getFirstDiagonalPawnCount(position);
        positions[4] = new Position(position.getLine() - firstDiagonalPawnCount, position.getColumn() - firstDiagonalPawnCount); // top left
        positions[5] = new Position(position.getLine() - firstDiagonalPawnCount, position.getColumn() + firstDiagonalPawnCount); // top right

        int secondDiagonalPawnCount = this.getSecondDiagonalPawnCount(position);
        positions[6] = new Position(position.getLine() + secondDiagonalPawnCount, position.getColumn() - secondDiagonalPawnCount); // bottom left
        positions[7] = new Position(position.getLine() + secondDiagonalPawnCount, position.getColumn() + secondDiagonalPawnCount); // bottom right
        return positions;
    }

    private int getFirstDiagonalPawnCount(Position position) {
        int count = 0;
        int diagonalEdge = Math.min(position.getLine(), position.getColumn());
        for (int line = position.getLine() - diagonalEdge, column = position.getColumn() - diagonalEdge; line < this.board.length && column < this.board.length; line++, column++) {
            if (this.board[line][column] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    private int getHorizontalPawnCount(Position position) {
        int count = 0;
        for (int column = 0; column < this.board.length; column++) {
            if (this.board[position.getLine()][column] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    private int getSecondDiagonalPawnCount(Position position) {
        int count = 0;
        int diagonalEdge = Math.min(this.board.length - position.getLine() - 1, position.getColumn());
        for (int line = position.getLine() + diagonalEdge, column = position.getColumn() - diagonalEdge; line >= 0 && column < this.board.length; line--, column++) {
            if (this.board[line][column] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    private int getVerticalPawnCount(Position position) {
        int count = 0;
        for (Pawn[] pawns : this.board) {
            if (pawns[position.getColumn()] != Pawn.EMPTY) {
                count++;
            }
        }

        return count;
    }

    @Override
    protected void setAllowedMoves() {
        this.allowedMoves.clear();

        for (Position position : this.generatePositionsFrom(this.selectedPawn)) {
            if (this.isMoveValid(position)) {
                this.allowedMoves.add(position);
            }
        }
    }

    private boolean isMoveValid(Position position) {
        boolean validMove = true;

        if (this.isPositionValid(position) && this.board[position.getLine()][position.getColumn()] != this.getPawnActualTurn()) { // destination must be on the board and can't be my pawn
            int deltaLine = position.getLine() - this.selectedPawn.getLine();
            int lineGradient = deltaLine == 0 ? 0 : Math.abs(deltaLine) / deltaLine; // line direction

            int deltaColumn = position.getColumn() - this.selectedPawn.getColumn();
            int columnGradient = deltaColumn == 0 ? 0 : Math.abs(deltaColumn) / deltaColumn; // column direction

            int line = this.selectedPawn.getLine() + lineGradient;
            int column = this.selectedPawn.getColumn() + columnGradient;
            while (this.isPositionValid(new Position(line, column)) && validMove && !(position.getLine() == line && position.getColumn() == column)) {
                if (this.board[line][column] == this.getPawnOpponentTurn()) { // can't go hover opponent's pawn
                    validMove = false;
                }
                line += lineGradient;
                column += columnGradient;
            }
        } else {
            validMove = false;
        }

        return validMove;
    }

    /**
     * Check if the position is on the board
     *
     * @param position the position to check
     * @return is position on board
     */
    private boolean isPositionValid(Position position) {
        return ((position.getLine() >= 0 && position.getLine() < this.board.length) && (position.getColumn() >= 0 && position.getColumn() < this.board[0].length));
    }

    private Pawn getPawnOpponentTurn() {
        return this.whiteTeamTurn ? Pawn.BLACK : Pawn.WHITE;
    }
}
