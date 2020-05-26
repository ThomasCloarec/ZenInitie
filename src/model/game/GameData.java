package model.game;

import com.google.gson.Gson;
import model.game.board.Board;
import model.game.team.Team;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * The GameData class. Used to keep all the data of a game instance in a single location (making it easier to load/save and to play in multiplayer mode with network).
 */
@SuppressWarnings("AssignmentOrReturnOfFieldWithMutableType")
public class GameData {
    /**
     * The allowed moves for the currently selected pawn
     */
    private final ArrayList<Position> allowedMoves = new ArrayList<>();
    /**
     * The board of the game
     */
    private final Board board = new Board();
    /**
     * The list of teams playing this game
     */
    private final Team[] teams = new Team[2];
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
     * Is the game running.
     */
    private boolean running = true;
    /**
     * The currently selectedPawn (or last one)
     */
    private Position selectedPawn;
    /**
     * The winner team
     */
    private Team winner;

    /**
     * Load game data.
     *
     * @param path the path
     * @return the game data
     */
    public static GameData load(String path) {
        GameData gameData = null;

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            //convert the json string back to object
            gameData = new Gson().fromJson(br, GameData.class);
        } catch (FileNotFoundException notFoundException) {
            notFoundException.printStackTrace();
        }

        return gameData;
    }

    /**
     * Save game data to a JSON file
     */
    public void save() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");

        try (Writer writer = new FileWriter("zen_game_" + dateFormat.format(Calendar.getInstance().getTime()) + ".json")) {
            // Convert the object to json string
            new Gson().toJson(this, writer);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "GameData{" +
                "teams=" + this.teams +
                ", allowedMoves=" + this.allowedMoves +
                ", board=" + this.board +
                ", aiMode=" + this.aiMode +
                ", duoMode=" + this.duoMode +
                ", currentTeamIndex=" + this.currentTeamIndex +
                ", movingPawn=" + this.movingPawn +
                ", running=" + this.running +
                ", selectedPawn=" + this.selectedPawn +
                '}';
    }

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
    public Team[] getTeams() {
        return this.teams;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public Team getWinner() {
        return this.winner;
    }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner(Team winner) {
        this.winner = winner;
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
