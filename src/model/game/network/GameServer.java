package model.game.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import model.game.GameData;
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
     * @param aiMode  the ai mode
     * @param duoMode the duo mode
     */
    public GameServer(boolean aiMode, boolean duoMode) {
        super(aiMode, duoMode);

        this.playerID = new Network.PlayerID(0, 0);

        if (aiMode && duoMode) { // ai mode and duo
            this.roomSize = 2;
        } else if (duoMode) { // not ai mode but duo
            this.roomSize = 4;
        } else { // not ai mode and not duo
            this.roomSize = 2;
        }

        this.server = new ZenServer();
        this.server.addListener(new ServerListener());
        this.server.launch();
    }

    private class ServerListener extends Listener {
        @Override
        public void connected(Connection connection) {
            super.connected(connection);
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
            }
        }

        @Override
        public void received(Connection connection, Object object) {
            if (object instanceof GameData) {
                int teamIndex = GameServer.this.gameData.getCurrentTeamIndex();
                int playerIndex = GameServer.this.gameData.getTeams().get(GameServer.this.gameData.getCurrentTeamIndex()).getCurrentPlayerIndex();
                if (GameServer.this.playerID.equals(teamIndex, playerIndex)) {
                    GameServer.this.server.sendToTCP(connection.getID(), GameServer.this.gameData);
                }
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
