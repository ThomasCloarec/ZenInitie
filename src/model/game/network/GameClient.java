package model.game.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import model.game.Game;
import model.game.GameData;
import utils.network.Network;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * The type Game client.
 */
public class GameClient extends Game {
    /**
     * The Client.
     */
    private final Client client;

    /**
     * Instantiates a new Game client.
     *
     * @param aiMode  the ai mode
     * @param duoMode the duo mode
     */
    public GameClient(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);
        Log.set(Log.LEVEL_TRACE);
        this.client = new Client();

        this.client.start();

        Network.register(this.client);

        this.client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {
                if (o instanceof GameData) {
                    GameData gameData = (GameData) o;
                    GameClient.this.setGameData(gameData);
                    System.out.println(GameClient.this.gameData);
                }
            }
        });

        InetAddress serverIP = null;
        int i = 0;
        while (serverIP == null && i + Network.BASE_UDP_PORT <= Network.MAX_UDP_PORT) {
            List<InetAddress> serversIP = this.client.discoverHosts(i + Network.BASE_UDP_PORT, 1000);
            if (!serversIP.isEmpty() && serversIP.get(0) != null) {
                try {
                    serverIP = serversIP.get(0);
                    this.client.connect(5000, serverIP, i + Network.BASE_TCP_PORT, i + Network.BASE_UDP_PORT);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            i++;
        }

        this.client.sendTCP("HEY");
    }
}
