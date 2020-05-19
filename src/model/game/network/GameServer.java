package model.game.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import model.game.GameData;
import model.game.Position;
import utils.network.Network;
import utils.network.ZenServer;

import java.util.function.Supplier;

/**
 * The type Game server.
 */
public class GameServer extends GameNetwork {
    /**
     * The Server.
     */
    private final ZenServer server;

    /**
     * Instantiates a new Game server.
     *
     * @param aiMode            the ai mode
     * @param duoMode           the duo mode
     * @param launchGameNetwork the launch game network
     * @param goMenu            the go menu
     */
    public GameServer(boolean aiMode, boolean duoMode, Supplier<Boolean> launchGameNetwork, Runnable goMenu) {
        super(aiMode, duoMode, launchGameNetwork, goMenu);

        this.playerID = new Network.PlayerID(0, 0);

        int roomSize;
        if (duoMode) { // not ai mode but duo
            roomSize = 4;
        } else { // not ai mode and not duo OR ai mode and duo
            roomSize = 2;
        }

        this.server = new ZenServer(roomSize);
        this.server.addListener(new ServerListener());
        this.server.launch();
    }

    /**
     * Stop.
     */
    @Override
    public void stop() {
        this.server.stop();
    }

    /**
     * Move selected pawn.
     *
     * @param position the position
     */
    @Override
    public void moveSelectedPawn(Position position) {
        super.moveSelectedPawn(position);
        this.server.sendToAllTCP(this.gameData);
    }

    /**
     * Sets selected pawn.
     *
     * @param position the position
     */
    @Override
    public void setSelectedPawn(Position position) {
        super.setSelectedPawn(position);
        this.server.sendToAllTCP(this.gameData);
    }

    /**
     * The type Server listener.
     */
    private class ServerListener extends Listener {
        /**
         * Connected.
         *
         * @param connection the connection
         */
        @Override
        public void connected(Connection connection) {
            super.connected(connection);

            GameServer.this.server.incrementAlreadyFilledRoom();

            // Give and send a PlayerID to the newly connected client
            Network.PlayerID playerID = new Network.PlayerID();
            if (GameServer.this.isAiMode() && GameServer.this.isDuoMode()) {
                playerID.playerID = 1;
                playerID.teamID = 0;
            } else if (GameServer.this.isDuoMode()) {
                playerID.playerID = GameServer.this.server.getAlreadyFilledRoom() % 2 == 0 ? 1 : 0;
                playerID.teamID = GameServer.this.server.getAlreadyFilledRoom() <= 2 ? 0 : 1;
            } else {
                playerID.playerID = 0;
                playerID.teamID = 1;
            }
            GameServer.this.server.sendToTCP(connection.getID(), playerID);

            // When everyone is here, send them the gameData
            if (GameServer.this.server.getAlreadyFilledRoom() == GameServer.this.server.getRoomSize()) {
                GameServer.this.server.sendToAllTCP(GameServer.this.gameData);
                GameServer.this.launchGameNetwork.get();
            }
        }

        /**
         * Disconnected.
         *
         * @param connection the connection
         */
        @Override
        public void disconnected(Connection connection) {
            super.disconnected(connection);
            GameServer.this.goMenu.run();
        }

        /**
         * Received.
         *
         * @param connection the connection
         * @param o          the object
         */
        @Override
        public void received(Connection connection, Object o) {
            if (o instanceof GameData) {
                GameServer.this.setGameData((GameData) o);
                GameServer.this.server.sendToAllExceptTCP(connection.getID(), GameServer.this.gameData);
                GameServer.this.notifyUpdateEverything();
                /*int teamIndex = GameServer.this.gameData.getCurrentTeamIndex();
                int playerIndex = GameServer.this.gameData.getTeams().get(GameServer.this.gameData.getCurrentTeamIndex()).getCurrentPlayerIndex();

                if (GameServer.this.playerID.equals(teamIndex, playerIndex)) {
                    GameServer.this.server.sendToTCP(connection.getID(), GameServer.this.gameData);
                }*/
            }
            /*if (object instanceof GameInit) {
                GameInit gameInit = (GameInit) object;
                System.out.println("server received : " + gameInit);
                SomeResponse response = new SomeResponse();
                response.text = "Thanks";
                connection.sendTCP(response);
            }*/
        }
    }
}
