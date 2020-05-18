package model.game;

import com.esotericsoftware.kryonet.Server;
import utils.network.Network;

import java.io.IOException;

/**
 * The type Game server.
 */
public class GameServer extends Game {
    private final Server server;

    public GameServer(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);
        boolean tcpPortNotFound = true;
        this.server = new Server();

        this.server.start();

        int tcpPort = Network.BASE_TCP_PORT;
        int udpPort = Network.BASE_UDP_PORT;

        while (tcpPortNotFound && tcpPort <= Network.MAX_TCP_PORT && udpPort <= Network.MAX_UDP_PORT) {
            try {
                this.server.bind(tcpPort, udpPort);
                System.out.println("Server created of port TCP : " + tcpPort + " and UDP : " + udpPort);
                tcpPortNotFound = false;
            } catch (IOException ioException) {
                tcpPort++;
                udpPort++;
            }
        }
    }
}
