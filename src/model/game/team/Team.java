package model.game.team;

import model.game.Pawn;

import java.util.ArrayList;

public class Team {
    private final ArrayList<Player> players = new ArrayList<>();
    private final TeamColor teamColor;
    private int currentPlayerIndex;

    public Team(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * @return has this team turn ended ?
     */
    public boolean nextPlayerPlay() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();

        return this.currentPlayerIndex != 0;
    }

    public boolean controlPawn(Pawn pawn) {
        return this.hasPawn(pawn) || pawn == Pawn.ZEN;
    }

    public boolean hasPawn(Pawn pawn) {
        return pawn.name().equals(this.teamColor.name());
    }

    public boolean enemyHasPawn(Pawn pawn) {
        return !this.hasPawn(pawn) && pawn != Pawn.ZEN && pawn != Pawn.EMPTY;
    }

    public TeamColor getTeamColor() {
        return this.teamColor;
    }
}