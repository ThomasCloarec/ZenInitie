package model.game.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import model.game.GameData;
import model.game.Position;
import utils.network.Network;
import utils.network.ZenClient;

import java.util.function.Supplier;

/**
 * The type Game client.
 */
public class GameClient extends GameNetwork {
    /**
     * The Client.
     */
    private final ZenClient client;

    /**
     * Instantiates a new Game client.
     *
     * @param aiMode            the ai mode
     * @param duoMode           the duo mode
     * @param launchGameNetwork the launch game network
     * @param goMenu            the go menu
     */
    public GameClient(boolean aiMode, boolean duoMode, Supplier<Boolean> launchGameNetwork, Runnable goMenu) {
        super(aiMode, duoMode, launchGameNetwork, goMenu);

        this.client = new ZenClient();
        this.client.addListener(new ClientListener());
        this.client.launch();

        this.client.sendTCP("HEY");
    }

    /**
     * Stop.
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
         * Disconnected.
         *
         * @param connection the connection
         */
        @Override
        public void disconnected(Connection connection) {
            super.disconnected(connection);
            GameClient.this.goMenu.run();
        }

        /**
         * Received.
         *
         * @param connection the connection
         * @param o          the o
         */
        @Override
        public void received(Connection connection, Object o) {
            if (o instanceof Network.PlayerID) {
                GameClient.this.playerID = (Network.PlayerID) o;
                System.out.println(GameClient.this.playerID);
            } else if (o instanceof GameData) {
                GameData gameData = (GameData) o;
                GameClient.this.setGameData(gameData);

                if (!GameClient.this.launchGameNetwork.get()) {
                    GameClient.this.notifyUpdateEverything();
                }
            }
        }
    }
}
