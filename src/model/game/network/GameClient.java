package model.game.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import model.game.GameData;
import model.game.Position;
import model.game.team.Team;
import utils.network.Network;
import utils.network.ZenClient;

/**
 * The type Game client (it communicates player's moves to the server)
 */
public class GameClient extends GameNetwork {
    /**
     * The Client.
     */
    private final ZenClient client;
    /**
     * The Player connected.
     */
    private boolean playerConnected;

    /**
     * Instantiates a new Game client.
     *
     * @param aiMode            the ai mode
     * @param duoMode           the duo mode
     * @param launchGameNetwork the launch game network
     * @param goMenu            the go menu
     */
    public GameClient(boolean aiMode, boolean duoMode, Runnable launchGameNetwork, Runnable goMenu) {
        super(aiMode, duoMode, launchGameNetwork, goMenu);

        this.client = new ZenClient();
        this.client.addListener(new ClientListener());
        this.client.launch();
    }

    /**
     * Stop the client.
     */
    @Override
    public void stop() {
        this.client.stop();
    }

    /**
     * Move selected pawn.
     *
     * @param position the position
     */
    @Override
    public void moveSelectedPawn(Position position) {
        super.moveSelectedPawn(position);
        this.client.sendTCP(this.gameData);
    }

    /**
     * Notify game winner.
     *
     * @param team the team
     */
    @Override
    protected void notifyGameWinner(Team team) {
        super.notifyGameWinner(team);
        this.client.sendTCP(this.gameData);
    }

    /**
     * Sets selected pawn.
     *
     * @param position the position
     */
    @Override
    public void setSelectedPawn(Position position) {
        super.setSelectedPawn(position);
        this.client.sendTCP(this.gameData);
    }

    /**
     * The type Client listener.
     */
    private class ClientListener extends Listener {
        /**
         * Client disconnected.
         *
         * @param connection the connection
         */
        @Override
        public void disconnected(Connection connection) {
            if (GameClient.this.playerConnected) {
                GameClient.this.goMenu.run();
            }
        }

        /**
         * Received an object from the server.
         *
         * @param connection the connection
         * @param o          the object
         */
        @Override
        public void received(Connection connection, Object o) {
            if (o instanceof Network.PlayerID) {
                GameClient.this.playerID = (Network.PlayerID) o;
                GameClient.this.playerConnected = true;
            } else if (o instanceof GameData) {
                GameData gameData = (GameData) o;
                GameClient.this.setGameData(gameData);

                if (GameClient.this.playerConnected) {
                    GameClient.this.launchGameNetwork.run();
                    GameClient.this.notifyUpdateEverything();
                }
            }
        }
    }
}
