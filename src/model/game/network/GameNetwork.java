package model.game.network;

import model.game.Game;
import utils.network.Network;

/**
 * The type Game network (used to be extends by GameClient and GameServer)
 */
public abstract class GameNetwork extends Game {
    /**
     * The Go menu method provided by the controller.
     */
    protected final Runnable goMenu;
    /**
     * The Launch game network method provided by the controller.
     */
    protected final Runnable launchGameNetwork;
    /**
     * The Player id.
     */
    protected Network.PlayerID playerID;

    /**
     * The Game constructor, add teams, players and start a game
     *
     * @param aiMode            is the game in ai mode (against computer) ?
     * @param duoMode           is the game in duo mode ?
     * @param launchGameNetwork the launch game network
     * @param goMenu            the go menu
     */
    protected GameNetwork(boolean aiMode, boolean duoMode, Runnable launchGameNetwork, Runnable goMenu) {
        super(aiMode, duoMode);
        this.launchGameNetwork = launchGameNetwork;
        this.goMenu = goMenu;
    }

    /**
     * Stop the client or server (depending on the child)
     */
    public abstract void stop();

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isOnline() {
        return super.isOnline();
    }

    /**
     * Is human user turn boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isHumanUserTurn() {
        return super.isHumanUserTurn() && this.playerID.equals(this.gameData.getCurrentTeamIndex(), this.getCurrentTeam().getCurrentPlayerIndex());
    }
}
