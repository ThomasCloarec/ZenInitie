package model.game.network;

import model.game.Game;
import utils.network.Network;

import java.util.function.Supplier;

/**
 * The type Game network.
 */
public abstract class GameNetwork extends Game {
    /**
     * The Go menu.
     */
    protected final Runnable goMenu;
    /**
     * The Is game network launched.
     */
    protected final Supplier<Boolean> isGameNetworkLaunched;
    /**
     * The Launch game network.
     */
    protected final Supplier<Boolean> launchGameNetwork;
    /**
     * The Player id.
     */
    protected Network.PlayerID playerID;

    /**
     * The Game constructor, add teams, players and start game
     *
     * @param aiMode                is the game in ai mode (against computer) ?
     * @param duoMode               is the game in duo mode ?
     * @param launchGameNetwork     the launch game network
     * @param goMenu                the go menu
     * @param isGameNetworkLaunched the is game network launched
     */
    public GameNetwork(boolean aiMode, boolean duoMode, Supplier<Boolean> launchGameNetwork, Runnable goMenu, Supplier<Boolean> isGameNetworkLaunched) {
        super(aiMode, duoMode);
        this.launchGameNetwork = launchGameNetwork;
        this.goMenu = goMenu;
        this.isGameNetworkLaunched = isGameNetworkLaunched;
    }

    /**
     * Stop.
     */
    public abstract void stop();
}
