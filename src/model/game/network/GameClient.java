package model.game.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import model.game.GameData;
import utils.network.Network;
import utils.network.ZenClient;

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
     * @param aiMode  the ai mode
     * @param duoMode the duo mode
     */
    public GameClient(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);

        this.client = new ZenClient();
        this.client.addListener(new ClientListener());
        this.client.launch();

        this.client.sendTCP("HEY");
    }

    private class ClientListener extends Listener {
        @Override
        public void received(Connection connection, Object o) {
            if (o instanceof Network.PlayerID) {
                GameClient.this.playerID = (Network.PlayerID) o;
                System.out.println(GameClient.this.playerID);
            } else if (o instanceof GameData) {
                GameData gameData = (GameData) o;
                GameClient.this.setGameData(gameData);
                System.out.println(GameClient.this.gameData);
            }
        }
    }
}
