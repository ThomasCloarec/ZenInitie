package utils.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The type PawnTest client.
 */
public class ZenClient extends Client {
    /**
     * The Connection index black list.
     */
    private final Collection<Integer> connectionIndexBlackList = new ArrayList<>(); // When a game is already running on a server, prevent the client to connect to it
    /**
     * The Connection try index.
     */
    private int connectionTryIndex;

    /**
     * Instantiates a new PawnTest client.
     */
    public ZenClient() {
        Log.set(Log.LEVEL_TRACE);
        this.start();
        Network.register(this);
        this.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {
                super.received(connection, o);
                if (o instanceof Network.Message) {
                    Network.Message message = (Network.Message) o;
                    if (message == Network.Message.ROOM_IS_FULL) {
                        ZenClient.this.connectionIndexBlackList.add(ZenClient.this.connectionTryIndex);
                        ZenClient.this.launch();
                    }
                }
            }
        });
    }

    /**
     * Launch.
     */
    public void launch() {
        new Thread(() -> {
            InetAddress serverIP = null;
            while (serverIP == null) {
                if (this.connectionTryIndex + Network.BASE_UDP_PORT > Network.MAX_UDP_PORT) { // Reset search
                    this.connectionTryIndex = 0;
                    String ip = "null";
                    try {
                        ip = JOptionPane.showInputDialog(null, "What's the IP address of the server ? (No server found on local network)", "");
                        this.connect(Integer.MAX_VALUE, ip, this.connectionTryIndex + Network.BASE_TCP_PORT, this.connectionTryIndex + Network.BASE_UDP_PORT);
                        serverIP = InetAddress.getByName(ip);
                    } catch (IOException ignored) {
                        JOptionPane.showMessageDialog(null, "No valid server on IP : \"" + ip + "\"");
                    }
                }

                List<InetAddress> serversIP = this.discoverHosts(this.connectionTryIndex + Network.BASE_UDP_PORT, 1000);
                if (!this.connectionIndexBlackList.contains(this.connectionTryIndex) && !serversIP.isEmpty() && serversIP.get(0) != null) {
                    try {
                        serverIP = serversIP.get(0);
                        this.connect(Integer.MAX_VALUE, serverIP, this.connectionTryIndex + Network.BASE_TCP_PORT, this.connectionTryIndex + Network.BASE_UDP_PORT);
                    } catch (IOException ioException) {
                        this.launch();
                    }
                } else {
                    this.connectionTryIndex++;
                }
            }
        }).start();
    }

    /**
     * Gets connection index black list.
     *
     * @return the connection index black list
     */
    public Collection<Integer> getConnectionIndexBlackList() {
        return Collections.unmodifiableCollection(this.connectionIndexBlackList);
    }
}
