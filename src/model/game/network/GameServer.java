package model.game.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import model.game.Game;
import utils.network.Network;

import java.io.IOException;

/**
 * The type Game server.
 */
public class GameServer extends Game {
    /**
     * The Server.
     */
    private final Server server;
    private final int roomSize;
    private int alreadyFilledRoom = 1;

    /**
     * Instantiates a new Game server.
     *
     * @param aiMode  the ai mode
     * @param duoMode the duo mode
     */
    public GameServer(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);

        if (aiMode && duoMode) { // ai mode and duo
            this.roomSize = 2;
        } else if (aiMode) { // ai mode but not duo TODO why one player would like a network ?
            this.roomSize = 1;
        } else if (duoMode) { // not ai mode but duo
            this.roomSize = 4;
        } else { // not ai mode and not duo
            this.roomSize = 2;
        }

        Log.set(Log.LEVEL_TRACE);
        boolean tcpPortNotFound = true;
        this.server = new Server();

        this.server.start();

        Network.register(this.server);

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

        this.server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                GameServer.this.server.sendToTCP(connection.getID(), GameServer.this.gameData);
                GameServer.this.alreadyFilledRoom++;
            }

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    System.out.println((String) object);
                }
                /*if (object instanceof GameInit) {
                    GameInit gameInit = (GameInit) object;
                    System.out.println("server received : " + gameInit);
                    SomeResponse response = new SomeResponse();
                    response.text = "Thanks";
                    connection.sendTCP(response);
                }*/
            }
        });
    }
}
