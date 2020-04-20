package model.game;

import model.Observable;
import view.sub_views.game_view.GameView;

import java.util.ArrayList;

import static model.utils.Util.getRandomBoolean;

public class Game extends Observable<GameView> {
    protected final boolean aiMode;
    protected final boolean duoMode;
    protected final boolean onlineMode;
    private final ArrayList<Position> allowedMoves = new ArrayList<>();
    private final Board board = new Board();
    protected boolean whiteTeamTurn = getRandomBoolean();
    private boolean firstPlayerInTeamTurn = true;
    private ArrayList<String> playersBlack;
    private ArrayList<String> playersWhite;
    private Position selectedPawn;

    public Game(boolean aiMode, boolean duoMode, boolean onlineMode) {
        this.aiMode = aiMode;
        this.duoMode = duoMode;
        this.onlineMode = onlineMode;
    }

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

    public boolean isPawnSelectable(Position position) {
        Pawn pawn = this.board.getArray()[position.getLine()][position.getColumn()];
        return pawn == this.getPawnActualTurn() || (pawn == Pawn.ZEN);
    }

    public void moveSelectedPawn(Position position) {
        this.board.getArray()[position.getLine()][position.getColumn()] = this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()];
        this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()] = Pawn.EMPTY;

        this.whiteTeamTurn = (this.duoMode && this.firstPlayerInTeamTurn) == this.whiteTeamTurn; // logic, isn't it ?
        this.firstPlayerInTeamTurn = this.duoMode ^ this.firstPlayerInTeamTurn; // ^ = xor (interesting no ?)
        this.notifyPawnMoved();
    }

    protected void notifyPawnMoved() {
        this.observers.forEach(gameView -> gameView.pawnMoved(this));
    }

    protected void notifyPawnSelected() {
        this.observers.forEach(gameView -> gameView.pawnSelected(this));
    }

    protected void setAllowedMoves() {
        this.allowedMoves.clear();

        for (Position position : this.generatePositionsFrom(this.selectedPawn)) {
            if (this.isMoveValid(position)) {
                this.allowedMoves.add(position);
            }
        }
    }

    private Position[] generatePositionsFrom(Position position) {
        Position[] positions = new Position[8];

        int verticalPawnCount = this.board.getVerticalPawnCount(position);
        positions[0] = new Position(position.getLine() - verticalPawnCount, position.getColumn()); // top
        positions[1] = new Position(position.getLine() + verticalPawnCount, position.getColumn()); // bottom

        int horizontalPawnCount = this.board.getHorizontalPawnCount(position);
        positions[2] = new Position(position.getLine(), position.getColumn() - horizontalPawnCount); // left
        positions[3] = new Position(position.getLine(), position.getColumn() + horizontalPawnCount); // right

        int firstDiagonalPawnCount = this.board.getFirstDiagonalPawnCount(position);
        positions[4] = new Position(position.getLine() - firstDiagonalPawnCount, position.getColumn() - firstDiagonalPawnCount); // top left
        positions[5] = new Position(position.getLine() - firstDiagonalPawnCount, position.getColumn() + firstDiagonalPawnCount); // top right

        int secondDiagonalPawnCount = this.board.getSecondDiagonalPawnCount(position);
        positions[6] = new Position(position.getLine() + secondDiagonalPawnCount, position.getColumn() - secondDiagonalPawnCount); // bottom left
        positions[7] = new Position(position.getLine() + secondDiagonalPawnCount, position.getColumn() + secondDiagonalPawnCount); // bottom right
        return positions;
    }

    private boolean isMoveValid(Position position) {
        boolean validMove = true;

        if (this.board.isPositionValid(position) && this.board.getArray()[position.getLine()][position.getColumn()] != this.getPawnActualTurn()) { // destination must be on the board and can't be my pawn
            int deltaLine = position.getLine() - this.selectedPawn.getLine();
            int lineGradient = deltaLine == 0 ? 0 : Math.abs(deltaLine) / deltaLine; // line direction

            int deltaColumn = position.getColumn() - this.selectedPawn.getColumn();
            int columnGradient = deltaColumn == 0 ? 0 : Math.abs(deltaColumn) / deltaColumn; // column direction

            int line = this.selectedPawn.getLine() + lineGradient;
            int column = this.selectedPawn.getColumn() + columnGradient;
            while (this.board.isPositionValid(new Position(line, column)) && validMove && !(position.getLine() == line && position.getColumn() == column)) {
                if (this.board.getArray()[line][column] == this.getPawnOpponentTurn()) { // can't go hover opponent's pawn
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

    @Override
    public void addObserver(GameView observer) {
        super.addObserver(observer);
        observer.start(this);
    }

    public ArrayList<Position> getAllowedMoves() {
        return this.allowedMoves;
    }

    public Pawn[][] getBoard() {
        return this.board.getArray();
    }

    public Pawn getPawnActualTurn() {
        return this.whiteTeamTurn ? Pawn.WHITE : Pawn.BLACK;
    }

    private Pawn getPawnOpponentTurn() {
        return this.whiteTeamTurn ? Pawn.BLACK : Pawn.WHITE;
    }

    public Position getSelectedPawn() {
        return this.selectedPawn;
    }

    public void setSelectedPawn(Position position) {
        this.selectedPawn = position;
        this.setAllowedMoves();
        this.notifyPawnSelected();
    }
}
