package model.game.team;

import model.game.board.Pawn;

import java.util.ArrayList;

/**
 * The type Team.
 */
public class Team {
    /**
     * The Players.
     */
    private final ArrayList<Player> players = new ArrayList<>();
    /**
     * The Team color.
     */
    private final TeamColor teamColor;
    /**
     * The Current player index.
     */
    private int currentPlayerIndex;

    /**
     * Instantiates a new Team.
     *
     * @param teamColor the team color
     */
    public Team(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Next player play boolean.
     *
     * @return has this team turn ended ?
     */
    public boolean nextPlayerPlay() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();

        return this.currentPlayerIndex != 0;
    }

    /**
     * Control pawn boolean.
     *
     * @param pawn the pawn
     * @return the boolean
     */
    public boolean controlPawn(Pawn pawn) {
        return this.hasPawn(pawn) || pawn == Pawn.ZEN;
    }

    /**
     * Has pawn boolean.
     *
     * @param pawn the pawn
     * @return the boolean
     */
    public boolean hasPawn(Pawn pawn) {
        String name = pawn.name();
        return name.equals(this.teamColor.name());
    }

    /**
     * Enemy has pawn boolean.
     *
     * @param pawn the pawn
     * @return the boolean
     */
    public boolean enemyHasPawn(Pawn pawn) {
        return !this.hasPawn(pawn) && pawn != Pawn.ZEN && pawn != Pawn.EMPTY;
    }

    /**
     * Gets current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return this.players.get(this.currentPlayerIndex);
    }

    /**
     * Gets current player name.
     *
     * @return the current player name
     */
    public String getCurrentPlayerName() {
        Player currentPlayer = this.getCurrentPlayer();
        return currentPlayer.getName();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.teamColor.name();
    }

    /**
     * Gets opponent team color.
     *
     * @return the opponent team color
     */
    public TeamColor getOpponentTeamColor() {
        TeamColor teamColor = null;
        if (this.teamColor == TeamColor.BLUE) {
            teamColor = TeamColor.RED;
        } else if (this.teamColor == TeamColor.RED) {
            teamColor = TeamColor.BLUE;
        }

        return teamColor;
    }

    /**
     * Gets team color.
     *
     * @return the team color
     */
    public TeamColor getTeamColor() {
        return this.teamColor;
    }
}
