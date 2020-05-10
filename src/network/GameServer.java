package network;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class GameServer extends Server {
    public GameServer() {
        boolean tcpPortNotFound = true;
        this.start();

        int tcpPort = Network.BASE_TCP_PORT;
        int udpPort = Network.BASE_UDP_PORT;

        while (tcpPortNotFound && tcpPort <= Network.MAX_TCP_PORT && udpPort <= Network.MAX_UDP_PORT) {
            try {
                this.bind(tcpPort, udpPort);
                System.out.println("Server created of port TCP : " + tcpPort + " and UDP : " + udpPort);
                tcpPortNotFound = false;
            } catch (IOException ioException) {
                tcpPort++;
                udpPort++;
            }
        }
    }
}
