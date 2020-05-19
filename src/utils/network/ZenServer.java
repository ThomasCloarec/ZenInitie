package utils.network;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

/**
 * The type Zen server.
 */
public class ZenServer extends Server {
    /**
     * The Room size.
     */
    private final int roomSize;
    /**
     * The Already filled room.
     */
    private int alreadyFilledRoom = 1;

    /**
     * Instantiates a new Zen server.
     *
     * @param roomSize the room size
     */
    public ZenServer(int roomSize) {
        this.roomSize = roomSize;

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
                System.out.println("Server created of port TCP : " + tcpPort + " and UDP : " + udpPort);
                tcpPortNotFound = false;
            } catch (IOException ioException) {
                tcpPort++;
                udpPort++;
            }
        }
    }

    /**
     * Increment already filled room.
     */
    public void incrementAlreadyFilledRoom() {
        this.alreadyFilledRoom++;
    }

    /**
     * Stop.
     */
    @Override
    public void stop() {
        super.stop();
        System.out.println("Server stopped");
    }

    /**
     * Gets already filled room.
     *
     * @return the already filled room
     */
    public int getAlreadyFilledRoom() {
        return this.alreadyFilledRoom;
    }

    /**
     * Gets room size.
     *
     * @return the room size
     */
    public int getRoomSize() {
        return this.roomSize;
    }
}
