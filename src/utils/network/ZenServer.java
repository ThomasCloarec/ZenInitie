package utils.network;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

public class ZenServer extends Server {
    public ZenServer() {
        Log.set(Log.LEVEL_TRACE);
        this.start();
        Network.register(this);
    }

    public void launch() {
        boolean tcpPortNotFound = true;

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
