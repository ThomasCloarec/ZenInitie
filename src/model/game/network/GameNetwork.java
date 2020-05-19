package model.game.network;

import model.game.Game;
import utils.network.Network;

import java.util.function.Supplier;

/**
 * The type Game network.
 */
public abstract class GameNetwork extends Game {
    /**
     * The Player id.
     */
    protected Network.PlayerID playerID;
    /**
     * The Go menu.
     */
    protected final Runnable goMenu;
    /**
     * The Launch game network.
     */
    protected final Supplier<Boolean> launchGameNetwork;

    /**
     * The Game constructor, add teams, players and start game
     *
     * @param aiMode            is the game in ai mode (against computer) ?
     * @param duoMode           is the game in duo mode ?
     * @param launchGameNetwork the launch game network
     * @param goMenu            the go menu
     */
    public GameNetwork(boolean aiMode, boolean duoMode, Supplier<Boolean> launchGameNetwork, Runnable goMenu) {
        super(aiMode, duoMode);
        this.launchGameNetwork = launchGameNetwork;
        this.goMenu = goMenu;
    }

    /**
     * Stop.
     */
    public abstract void stop();
}
