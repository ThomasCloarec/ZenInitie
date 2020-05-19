package model.game.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import model.game.GameData;
import model.game.Position;
import model.game.team.Team;
import utils.network.Network;
import utils.network.ZenServer;

/**
 * The type Game server.
 */
public class GameServer extends GameNetwork {
    /**
     * The Room size.
     */
    private final int roomSize;
    /**
     * The Server.
     */
    private final ZenServer server;
    /**
     * The Already filled room.
     */
    private int alreadyFilledRoom = 1;

    /**
     * Instantiates a new Game server.
     *
     * @param aiMode            the ai mode
     * @param duoMode           the duo mode
     * @param launchGameNetwork the launch game network
     * @param goMenu            the go menu
     */
    public GameServer(boolean aiMode, boolean duoMode, Runnable launchGameNetwork, Runnable goMenu) {
        super(aiMode, duoMode, launchGameNetwork, goMenu);

        this.playerID = new Network.PlayerID(0, 0);

        if (duoMode) { // not ai mode but duo
            this.roomSize = 4;
        } else { // not ai mode and not duo OR ai mode and duo
            this.roomSize = 2;
        }

        this.server = new ZenServer();
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
     * Notify game winner.
     *
     * @param team the team
     */
    @Override
    protected void notifyGameWinner(Team team) {
        super.notifyGameWinner(team);
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

            // If the server is full, refuse the new connection
            if (GameServer.this.alreadyFilledRoom == GameServer.this.roomSize) {
                GameServer.this.server.sendToTCP(connection.getID(), Network.Message.ROOM_IS_FULL);
                connection.close();
            } else {
                GameServer.this.server.addConnectionIDToList(connection.getID());
                GameServer.this.alreadyFilledRoom++;

                // Give and send a PlayerID to the newly connected client
                Network.PlayerID playerID = new Network.PlayerID();
                if (GameServer.this.isAiMode() && GameServer.this.isDuoMode()) {
                    playerID.playerID = 1;
                    playerID.teamID = 0;
                } else if (GameServer.this.isDuoMode()) {
                    playerID.playerID = GameServer.this.alreadyFilledRoom % 2 == 0 ? 1 : 0;
                    playerID.teamID = GameServer.this.alreadyFilledRoom <= 2 ? 0 : 1;
                } else {
                    playerID.playerID = 0;
                    playerID.teamID = 1;
                }
                GameServer.this.server.sendToTCP(connection.getID(), playerID);

                // When everyone is here, send them the gameData
                if (GameServer.this.alreadyFilledRoom == GameServer.this.roomSize) {
                    GameServer.this.server.sendToAllTCP(GameServer.this.gameData);
                    GameServer.this.launchGameNetwork.run();
                }
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
            if (GameServer.this.server.getConnectionIDList().contains(connection.getID())) {
                GameServer.this.goMenu.run();
            }
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
            }
        }
    }
}
