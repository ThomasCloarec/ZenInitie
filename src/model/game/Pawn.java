package model.game;

import model.game.team.TeamColor;

/**
 * The enum Pawn.
 */
public enum Pawn {
    /**
     * Empty pawn.
     */
    EMPTY,
    /**
     * Red pawn.
     */
    RED,
    /**
     * Blue pawn.
     */
    BLUE,
    /**
     * Zen pawn.
     */
    ZEN;

    /**
     * Gets pawn from team color.
     *
     * @param teamColor the team color
     * @return the pawn from team color
     */
    public static Pawn getPawnFromTeamColor(TeamColor teamColor) {
        Pawn pawn = null;
        if (Pawn.hasName(teamColor.name())) {
            pawn = Pawn.valueOf(teamColor.name());
        }

        return pawn;
    }

    /**
     * Has name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    private static boolean hasName(String name) {
        boolean ret = false;

        int i = 0;
        while (i < Pawn.values().length && !ret) {
            ret = Pawn.values()[i].name().equals(name);
            i++;
        }

        return ret;
    }
}
