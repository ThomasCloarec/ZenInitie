package model.game;

import model.game.team.Player;
import model.game.team.Team;
import model.game.team.TeamColor;
import utils.observer.Observable;
import view.subviews.gameview.GameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The global Game class, it handles a whole game of Zen l'Initié from the start to the end.
 */
public class Game extends Observable<GameView> {
    /**
     * is the game in ai mode (against computer) ?
     */
    protected final boolean aiMode;
    /**
     * is the game in duo mode ?
     */
    protected final boolean duoMode;
    /**
     * is the game in online mode ?
     */
    protected final boolean onlineMode;
    /**
     * The allowed moves for the current selected pawn
     */
    private final ArrayList<Position> allowedMoves = new ArrayList<>();
    /**
     * The board of the game
     */
    private final Board board = new Board();
    /**
     * The list of teams playing this game
     */
    private final ArrayList<Team> teams = new ArrayList<>(2);
    /**
     * The current index of the team playing
     */
    private int currentTeamIndex;
    /**
     * Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move
     */
    private boolean movingPawn;
    /**
     * The currently selectedPawn (or last one)
     */
    private Position selectedPawn;

    /**
     * The Game constructor, add teams, players and start game
     *
     * @param aiMode     is the game in ai mode (against computer) ?
     * @param duoMode    is the game in duo mode ?
     * @param onlineMode is the game in online mode ?
     */
    public Game(boolean aiMode, boolean duoMode, boolean onlineMode) {
        this.aiMode = aiMode;
        this.duoMode = duoMode;
        this.onlineMode = onlineMode;

        this.teams.add(new Team(TeamColor.BLUE));
        this.teams.add(new Team(TeamColor.RED));
        if (this.duoMode) {
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

    /**
     * Check if a move is allowed
     *
     * @param move the position of the move
     * @return is the move allowed
     */
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

    /**
     * Check if a pawn is selectable
     *
     * @param position the position of the selection
     * @return is the position selectable
     */
    public boolean isPawnSelectable(Position position) {
        Pawn pawn = this.board.getArray()[position.getLine()][position.getColumn()];
        Team currentTeam = this.getCurrentTeam();
        return currentTeam.controlPawn(pawn);
    }

    /**
     * Move the selected pawn to the target position
     *
     * @param position the target position
     */
    public void moveSelectedPawn(Position position) {
        this.movingPawn = false;
        this.board.getArray()[position.getLine()][position.getColumn()] = this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()];
        this.board.getArray()[this.selectedPawn.getLine()][this.selectedPawn.getColumn()] = Pawn.EMPTY;

        Team currentTeam = this.getCurrentTeam();
        if (!currentTeam.nextPlayerPlay()) {
            this.currentTeamIndex = (this.currentTeamIndex + 1) % this.teams.size();
        }
        this.notifyPawnMoved();

        if (this.isWin()) {
            if (this.isOpponentTeamWin()) {
                System.out.println("DRAW");
            } else {
                System.out.println("WIN");
            }
        }
    }

    private void notifyPawnMoved() {
        this.forEachObserver(gameView -> gameView.pawnMoved(this));
    }

    private void notifyPawnSelected() {
        this.forEachObserver(gameView -> gameView.pawnSelected(this));
    }

    private void setAllowedMoves() {
        this.allowedMoves.clear();

        for (Position position : this.board.generatePositionsFrom(this.selectedPawn)) {
            if (this.isMoveValid(position)) {
                this.allowedMoves.add(position);
            }
        }
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

    private boolean isTeamWin(TeamColor teamColor) {
        Pawn pawn = Pawn.getPawnFromTeamColor(teamColor);

        boolean win = true;
        List<Position[]> checkArray = this.board.getPawnPositionsAround(Pawn.ZEN, pawn);

        int i = 0;
        while (i < checkArray.size() && win) {
            Position[] positions = checkArray.get(i);
            int j = 0;

            win = false;
            while (j < positions.length && !win) {
                win = this.getCurrentTeam().controlPawn(this.board.getPawn(positions[j]));
                j++;
            }
            i++;
        }

        return win;
    }

    /**
     * Set the selected pawn
     *
     * @param position the position of the pawn to select
     */
    public void setSelectedPawn(Position position) {
        this.movingPawn = true;
        this.selectedPawn = position;
        this.setAllowedMoves();
        this.notifyPawnSelected();
    }

    /**
     * Get the allowed moves
     *
     * @return the allowed moves
     */
    public List<Position> getAllowedMoves() {
        return Collections.unmodifiableList(this.allowedMoves);
    }

    /**
     * Get the game board array
     *
     * @return the game board array
     */
    public Pawn[][] getBoardArray() {
        return this.board.getArray();
    }

    /**
     * Get the game board array size
     *
     * @return the game board array size
     */
    public int getBoardSize() {
        return this.getBoardArray().length;
    }

    /**
     * Get the player's name of the one playing of the team playing during this turn
     *
     * @return the player's name
     */
    public String getCurrentPlayerName() {
        Team currentTeam = this.getCurrentTeam();
        return currentTeam.getCurrentPlayerName();
    }

    /**
     * Get the team playing during this turn
     *
     * @return the team
     */
    public Team getCurrentTeam() {
        return this.teams.get(this.currentTeamIndex);
    }

    /**
     * Gets is the game in ai mode against computer ?.
     *
     * @return Value of is the game in ai mode against computer ?.
     */
    public boolean isAiMode() {
        return this.aiMode;
    }

    /**
     * Gets is the game in duo mode ?.
     *
     * @return Value of is the game in duo mode ?.
     */
    public boolean isDuoMode() {
        return this.duoMode;
    }

    /**
     * Gets Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move.
     *
     * @return Value of Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move.
     */
    public boolean isMovingPawn() {
        return this.movingPawn;
    }

    private boolean isOpponentTeamWin() {
        return this.isTeamWin(this.getCurrentTeam().getOpponentTeamColor());
    }

    private boolean isWin() {
        return this.isTeamWin(this.getCurrentTeam().getTeamColor());
    }
}
