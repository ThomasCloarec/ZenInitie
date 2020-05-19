package utils.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * The type Zen client.
 */
public class ZenClient extends Client {
    /**
     * Instantiates a new Zen client.
     */
    public ZenClient() {
        Log.set(Log.LEVEL_TRACE);
        this.start();
        Network.register(this);
    }

    /**
     * Launch.
     */
    public void launch() {
        new Thread(() -> {
            InetAddress serverIP = null;
            int i = 0;
            while (serverIP == null) {
                if (i + Network.BASE_UDP_PORT > Network.MAX_UDP_PORT) { // Reset search
                    i = 0;
                }

                List<InetAddress> serversIP = this.discoverHosts(i + Network.BASE_UDP_PORT, 1000);
                if (!serversIP.isEmpty() && serversIP.get(0) != null) {
                    try {
                        serverIP = serversIP.get(0);
                        this.connect(5000, serverIP, i + Network.BASE_TCP_PORT, i + Network.BASE_UDP_PORT);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                i++;
            }
        }).start();
    }

    @Override
    public void stop() {
        super.stop();
        System.out.println("Client stopped");
    }
}
