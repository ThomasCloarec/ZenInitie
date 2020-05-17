package model.game;

import model.game.team.TeamColor;

public enum Pawn {
    EMPTY,
    RED,
    BLUE,
    ZEN;

    public static Pawn getPawnFromTeamColor(TeamColor teamColor) {
        Pawn pawn = null;
        if (Pawn.hasName(teamColor.name())) {
            pawn = Pawn.valueOf(teamColor.name());
        }

        return pawn;
    }

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
