package model.game;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import utils.network.Network;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * The type Game client.
 */
public class GameClient extends Game {
    private final Client client;

    public GameClient(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);

        this.client = new Client();

        this.client.start();

        String serverIP = null;
        int i = 0;
        while (serverIP == null && i + Network.BASE_UDP_PORT <= Network.MAX_UDP_PORT) {
            List<InetAddress> serversIP = this.client.discoverHosts(i + Network.BASE_UDP_PORT, 1000);
            if (!serversIP.isEmpty()) {
                serverIP = serversIP.get(0).getCanonicalHostName();
            }

            i++;
        }
        try {
            this.client.connect(5000, serverIP, i + Network.BASE_TCP_PORT);
            System.out.println("CONNECTED TO " + serverIP);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        this.client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {
                if (o instanceof GameData) {
                    GameData gameData = (GameData) o;
                }
            }
        });
    }
}
