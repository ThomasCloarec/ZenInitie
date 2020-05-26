package utils.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type PawnTest server.
 */
public class ZenServer extends Server {
    /**
     * The Connection id list.
     */
    private final List<Integer> connectionIDList = new ArrayList<>();

    /**
     * Instantiates a new PawnTest server.
     */
    public ZenServer() {
        Log.set(Log.LEVEL_TRACE);
        this.start();
        Network.register(this);
    }

    /**
     * Launch.
     */
    public void launch() {
        boolean tcpPortNotFound = true;

        int tcpPort = Network.BASE_TCP_PORT;
        int udpPort = Network.BASE_UDP_PORT;

        while (tcpPortNotFound && tcpPort <= Network.MAX_TCP_PORT && udpPort <= Network.MAX_UDP_PORT) {
            try {
                this.bind(tcpPort, udpPort);
                tcpPortNotFound = false;
            } catch (IOException ioException) {
                tcpPort++;
                udpPort++;
            }
        }
    }

    /**
     * Add connection id to list.
     *
     * @param connectionID the connection id
     */
    public void addConnectionIDToList(Integer connectionID) {
        this.connectionIDList.add(connectionID);
    }

    /**
     * Send to all tcp.
     *
     * @param o the o
     */
    @Override
    public void sendToAllTCP(Object o) {
        for (Connection connection : this.getConnections()) {
            if (this.connectionIDList.contains(connection.getID())) {
                connection.sendTCP(o);
            }
        }
    }

    /**
     * Send to all except tcp.
     *
     * @param i the
     * @param o the o
     */
    @Override
    public void sendToAllExceptTCP(int i, Object o) {
        for (Connection connection : this.getConnections()) {
            if (connection.getID() != i && this.connectionIDList.contains(connection.getID())) {
                connection.sendTCP(o);
            }
        }
    }

    /**
     * Gets connection id list.
     *
     * @return the connection id list
     */
    public List<Integer> getConnectionIDList() {
        return Collections.unmodifiableList(this.connectionIDList);
    }
}
