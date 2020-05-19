package model.game.network;

import model.game.Game;
import utils.network.Network;

/**
 * The type Game network.
 */
public abstract class GameNetwork extends Game {
    /**
     * The Go menu.
     */
    protected final Runnable goMenu;

    /**
     * The Launch game network.
     */
    protected final Runnable launchGameNetwork;
    /**
     * The Player id.
     */
    protected Network.PlayerID playerID;

    /**
     * The Game constructor, add teams, players and start game
     *
     * @param aiMode            is the game in ai mode (against computer) ?
     * @param duoMode           is the game in duo mode ?
     * @param launchGameNetwork the launch game network
     * @param goMenu            the go menu
     */
    public GameNetwork(boolean aiMode, boolean duoMode, Runnable launchGameNetwork, Runnable goMenu) {
        super(aiMode, duoMode);
        this.launchGameNetwork = launchGameNetwork;
        this.goMenu = goMenu;
    }

    /**
     * Stop.
     */
    public abstract void stop();
}
