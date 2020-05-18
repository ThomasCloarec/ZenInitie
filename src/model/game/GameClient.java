package model.game;

import com.esotericsoftware.kryonet.Client;
import utils.network.Network;

/**
 * The type Game client.
 */
public class GameClient extends Game {
    private final Client client;

    public GameClient(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);
        this.client = new Client();

        this.client.start();
        for (int i = Network.BASE_UDP_PORT; i <= Network.MAX_UDP_PORT; i++) {
            System.out.println("host on : " + i + " are : " + this.client.discoverHosts(i, 1000));
        }
    }
}
