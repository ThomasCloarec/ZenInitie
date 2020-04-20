package model.game;

import model.Observable;
import model.game.team.Player;
import model.game.team.Team;
import model.game.team.TeamColor;
import view.sub_views.game_view.GameView;

import java.util.ArrayList;

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
            if (move.getLine() == this.allowedMoves.get(i).getLine() && move.getColumn() == this.allowedMoves.get(i).getColumn()) {
                allowed = true;
            }
            i++;
        }

        return allowed;
    }

    public boolean isPawnSelectable(Position position) {
        Pawn pawn = this.board.getArray()[position.getLine()][position.getColumn()];
        return this.getCurrentTeam().controlPawn(pawn);
    }

    public void moveSelectedPawn(Position position) {
        this.board.getArray()[position.getLine()][position.getColumn()] = this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()];
        this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()] = Pawn.EMPTY;

        if (!this.getCurrentTeam().nextPlayerPlay()) {
            this.currentTeamIndex = (this.currentTeamIndex + 1) % this.teams.size();
        }
        this.notifyPawnMoved();
    }

    private void notifyPawnMoved() {
        this.observers.forEach(gameView -> gameView.pawnMoved(this));
    }

    private void notifyPawnSelected() {
        this.observers.forEach(gameView -> gameView.pawnSelected(this));
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

        if (this.board.isPositionValid(position) && !this.getCurrentTeam().controlPawn(this.board.getArray()[position.getLine()][position.getColumn()])) { // destination must be on the board and can't be my pawn
            int deltaLine = position.getLine() - this.selectedPawn.getLine();
            int lineGradient = deltaLine == 0 ? 0 : Math.abs(deltaLine) / deltaLine; // line direction

            int deltaColumn = position.getColumn() - this.selectedPawn.getColumn();
            int columnGradient = deltaColumn == 0 ? 0 : Math.abs(deltaColumn) / deltaColumn; // column direction

            int line = this.selectedPawn.getLine() + lineGradient;
            int column = this.selectedPawn.getColumn() + columnGradient;
            while (this.board.isPositionValid(new Position(line, column)) && validMove && !(position.getLine() == line && position.getColumn() == column)) {
                if (this.getCurrentTeam().enemyHasPawn(this.board.getArray()[line][column])) { // can't go hover opponent's pawn
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

    public void setSelectedPawn(Position position) {
        this.selectedPawn = position;
        this.setAllowedMoves();
        this.notifyPawnSelected();
    }

    public ArrayList<Position> getAllowedMoves() {
        return this.allowedMoves;
    }

    public Pawn[][] getBoard() {
        return this.board.getArray();
    }

    private Team getCurrentTeam() {
        return this.getCurrentTeam();
    }

    public TeamColor getCurrentTeamColor() {
        return this.getCurrentTeam().getTeamColor();
    }
}
