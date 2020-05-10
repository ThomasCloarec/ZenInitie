package model.game;

import model.game.team.Player;
import model.game.team.Team;
import model.game.team.TeamColor;
import utils.observer.Observable;
import view.subviews.gameview.GameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game extends Observable<GameView> {
    protected final boolean aiMode;
    protected final boolean onlineMode;
    private final ArrayList<Position> allowedMoves = new ArrayList<>();
    private final Board board = new Board();
    private final ArrayList<Team> teams = new ArrayList<>(2);
    private int currentTeamIndex;
    private Position selectedPawn;

    public Game(boolean aiMode, boolean duoMode, boolean onlineMode) {
        this.aiMode = aiMode;
        this.onlineMode = onlineMode;

        this.teams.add(new Team(TeamColor.BLACK));
        this.teams.add(new Team(TeamColor.WHITE));
        if (duoMode) {
            for (Team team : this.teams) {
                team.addPlayer(new Player("Player 1"));
                team.addPlayer(new Player("Player 2"));
            }
        } else {
            for (Team team : this.teams) {
                team.addPlayer(new Player("Player 1"));
            }
        }

        this.currentTeamIndex = (int) (Math.random() * this.teams.size());
    }

    public boolean isMoveAllowed(Position move) {
        boolean allowed = false;

        int i = 0;
        while (i < this.allowedMoves.size() && !allowed) {
            Position position = this.allowedMoves.get(i);
            if (move.getLine() == position.getLine() && move.getColumn() == position.getColumn()) {
                allowed = true;
            }
            i++;
        }

        return allowed;
    }

    public boolean isPawnSelectable(Position position) {
        Pawn pawn = this.board.getArray()[position.getLine()][position.getColumn()];
        Team currentTeam = this.getCurrentTeam();
        return currentTeam.controlPawn(pawn);
    }

    public void moveSelectedPawn(Position position) {
        this.board.getArray()[position.getLine()][position.getColumn()] = this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()];
        this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()] = Pawn.EMPTY;

        Team currentTeam = this.getCurrentTeam();
        if (!currentTeam.nextPlayerPlay()) {
            this.currentTeamIndex = (this.currentTeamIndex + 1) % this.teams.size();
        }
        this.notifyPawnMoved();
    }

    private void notifyPawnMoved() {
        this.forEachObserver(gameView -> gameView.pawnMoved(this));
    }

    private void notifyPawnSelected() {
        this.forEachObserver(gameView -> gameView.pawnSelected(this));
    }

    private void setAllowedMoves() {
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

        int diagonal1PawnCount = this.board.getFirstDiagonalPawnCount(position);
        positions[4] = new Position(position.getLine() - diagonal1PawnCount, position.getColumn() - diagonal1PawnCount); // top left
        positions[5] = new Position(position.getLine() - diagonal1PawnCount, position.getColumn() + diagonal1PawnCount); // top right

        int diagonal2PawnCount = this.board.getSecondDiagonalPawnCount(position);
        positions[6] = new Position(position.getLine() + diagonal2PawnCount, position.getColumn() - diagonal2PawnCount); // bottom left
        positions[7] = new Position(position.getLine() + diagonal2PawnCount, position.getColumn() + diagonal2PawnCount); // bottom right
        return positions;
    }

    private boolean isMoveValid(Position position) {
        boolean validMove = true;

        Team currentTeam = this.getCurrentTeam();
        if (this.board.isPositionValid(position) && !currentTeam.controlPawn(this.board.getArray()[position.getLine()][position.getColumn()])) { // destination must be on the board and can't be my pawn
            int deltaLine = position.getLine() - this.selectedPawn.getLine();
            int lineGradient = deltaLine == 0 ? 0 : Math.abs(deltaLine) / deltaLine; // line direction

            int deltaColumn = position.getColumn() - this.selectedPawn.getColumn();
            int columnGradient = deltaColumn == 0 ? 0 : Math.abs(deltaColumn) / deltaColumn; // column direction

            int line = this.selectedPawn.getLine() + lineGradient;
            int column = this.selectedPawn.getColumn() + columnGradient;
            while (this.board.isPositionValid(new Position(line, column)) && validMove && !(position.getLine() == line && position.getColumn() == column)) {
                if (currentTeam.enemyHasPawn(this.board.getArray()[line][column])) { // can't go hover opponent's pawn
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
    protected void notifyUpdateEverything(GameView observer) {

    }

    @Override
    public void addObserver(GameView observer) {
        super.addObserver(observer);
        observer.start(this);
    }

    public void setSelectedPawn(Position position) {
        this.selectedPawn = position;
        this.setAllowedMoves();
        this.notifyPawnSelected();
    }

    public List<Position> getAllowedMoves() {
        return Collections.unmodifiableList(this.allowedMoves);
    }

    public Pawn[][] getBoardArray() {
        return this.board.getArray();
    }

    public int getBoardSize() {
        return this.getBoardArray().length;
    }

    public String getCurrentPlayerName() {
        Team currentTeam = this.getCurrentTeam();
        return currentTeam.getCurrentPlayerName();
    }

    public Team getCurrentTeam() {
        return this.teams.get(this.currentTeamIndex);
    }

    public String getCurrentTeamName() {
        Team currentTeam = this.getCurrentTeam();
        return currentTeam.getName();
    }
}
