package network;

import com.esotericsoftware.kryonet.Client;

/**
 * The type Game client.
 */
public class GameClient extends Client {
    /**
     * Instantiates a new Game client.
     */
    public GameClient() {
        this.start();
        for (int i = Network.BASE_UDP_PORT; i <= Network.MAX_UDP_PORT; i++) {
            System.out.println("host on : " + i + " are : " + this.discoverHosts(i, 1000));
        }
    }
}
