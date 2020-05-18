package model.game;

import model.game.team.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game data.
 */
@SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
public class GameData {
    /**
     * The list of teams playing this game
     */
    protected final List<Team> teams = new ArrayList<>(2);
    /**
     * The allowed moves for the current selected pawn
     */
    private final ArrayList<Position> allowedMoves = new ArrayList<>();
    /**
     * The board of the game
     */
    private final Board board = new Board();
    /**
     * is the game in ai mode (against computer) ?
     */
    public boolean aiMode;
    /**
     * is the game in duo mode ?
     */
    public boolean duoMode;
    /**
     * The current index of the team playing
     */
    private int currentTeamIndex;
    /**
     * Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move
     */
    private boolean movingPawn;
    /**
     * The Finished.
     */
    private boolean running = true;
    /**
     * The currently selectedPawn (or last one)
     */
    private Position selectedPawn;

    /**
     * Gets allowed moves.
     *
     * @return the allowed moves
     */
    public ArrayList<Position> getAllowedMoves() {
        return this.allowedMoves;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Gets current team index.
     *
     * @return the current team index
     */
    public int getCurrentTeamIndex() {
        return this.currentTeamIndex;
    }

    /**
     * Sets current team index.
     *
     * @param currentTeamIndex the current team index
     */
    public void setCurrentTeamIndex(int currentTeamIndex) {
        this.currentTeamIndex = currentTeamIndex;
    }

    /**
     * Gets selected pawn.
     *
     * @return the selected pawn
     */
    public Position getSelectedPawn() {
        return this.selectedPawn;
    }

    /**
     * Sets selected pawn.
     *
     * @param selectedPawn the selected pawn
     */
    public void setSelectedPawn(Position selectedPawn) {
        this.selectedPawn = selectedPawn;
    }

    /**
     * Gets teams.
     *
     * @return the teams
     */
    public List<Team> getTeams() {
        return this.teams;
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
     * Sets ai mode.
     *
     * @param aiMode the ai mode
     */
    public void setAiMode(boolean aiMode) {
        this.aiMode = aiMode;
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
     * Sets duo mode.
     *
     * @param duoMode the duo mode
     */
    public void setDuoMode(boolean duoMode) {
        this.duoMode = duoMode;
    }

    /**
     * Gets Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move.
     *
     * @return Value of Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move.
     */
    public boolean isMovingPawn() {
        return this.movingPawn;
    }

    /**
     * Sets moving pawn.
     *
     * @param movingPawn the moving pawn
     */
    public void setMovingPawn(boolean movingPawn) {
        this.movingPawn = movingPawn;
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Sets running.
     *
     * @param running the running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
}
