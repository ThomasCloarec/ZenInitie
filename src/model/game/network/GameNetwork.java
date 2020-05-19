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
    protected final Runnable launchGameNetwork;

    /**
     * The Game constructor, add teams, players and start game
     *
     * @param aiMode  is the game in ai mode (against computer) ?
     * @param duoMode is the game in duo mode ?
     */
    public GameNetwork(boolean aiMode, boolean duoMode, Runnable launchGameNetwork) {
        super(aiMode, duoMode);
        this.launchGameNetwork = launchGameNetwork;
    }

    public abstract void stop();
}
