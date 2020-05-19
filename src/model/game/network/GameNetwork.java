package model.game.network;

import model.game.Game;
import utils.network.Network;

/**
 * The type Game network.
 */
public abstract class GameNetwork extends Game {
    /**
     * The Player id.
     */
    protected Network.PlayerID playerID;

    /**
     * The Game constructor, add teams, players and start game
     *
     * @param aiMode  is the game in ai mode (against computer) ?
     * @param duoMode is the game in duo mode ?
     */
    public GameNetwork(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);
    }
}
