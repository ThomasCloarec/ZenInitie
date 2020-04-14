package model.game;

import model.Observable;
import view.sub_views.game_view.GameView;

import java.util.ArrayList;

import static model.utils.Util.getRandomBoolean;

public abstract class Game extends Observable<GameView> {
    protected final boolean aiMode;
    protected final ArrayList<Position> allowedMoves = new ArrayList<>();
    protected final Pawn[][] board = {
            {Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY},
            {Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.ZEN, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.WHITE},
            {Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.WHITE, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY},
            {Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.BLACK, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY, Pawn.EMPTY}
    };
    protected final boolean duoMode;
    protected final boolean onlineMode;
    protected Position selectedPawn;
    protected boolean whiteTeamTurn = getRandomBoolean();

    public Game(boolean aiMode, boolean duoMode, boolean onlineMode) {
        this.aiMode = aiMode;
        this.duoMode = duoMode;
        this.onlineMode = onlineMode;
    }

    public abstract boolean isMoveAllowed(Position move);

    public abstract boolean isPawnSelectable(Position position);

    public abstract void moveSelectedPawn(Position position);

    protected abstract void setAllowedMoves();

    @Override
    public void addObserver(GameView observer) {
        super.addObserver(observer);
        observer.start(this);
    }

    protected void notifyPawnMoved() {
        this.observers.forEach(gameView -> gameView.pawnMoved(this));
    }

    protected void notifyPawnSelected() {
        this.observers.forEach(gameView -> gameView.pawnSelected(this));
    }

    public ArrayList<Position> getAllowedMoves() {
        return this.allowedMoves;
    }

    public Pawn[][] getBoard() {
        return this.board;
    }

    public Pawn getPawnActualTurn() {
        return this.whiteTeamTurn ? Pawn.WHITE : Pawn.BLACK;
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
