package utils.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import model.game.GameData;
import model.game.Position;
import model.game.board.Board;
import model.game.board.Pawn;
import model.game.team.Player;
import model.game.team.Team;
import model.game.team.TeamColor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Network.
 */
public final class Network {
    /**
     * The Base tcp port.
     */
    public static final int BASE_TCP_PORT = 31415;
    /**
     * The Base udp port.
     */
    public static final int BASE_UDP_PORT = 31425;
    /**
     * The Port search range.
     */
    public static final int PORT_SEARCH_RANGE = 3;
    /**
     * The Max tcp port.
     */
    public static final int MAX_TCP_PORT = Network.BASE_TCP_PORT + Network.PORT_SEARCH_RANGE - 1;
    /**
     * The Max udp port.
     */
    public static final int MAX_UDP_PORT = Network.BASE_UDP_PORT + Network.PORT_SEARCH_RANGE - 1;

    /**
     * Instantiates a new Network.
     */
    private Network() {

    }

    /**
     * Register send-able classes
     *
     * @param endPoint the end point
     */
    static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(GameData.class);
        kryo.register(ArrayList.class);
        kryo.register(Position.class);
        kryo.register(Board.class);
        kryo.register(List.class);
        kryo.register(Team.class);
        kryo.register(Pawn.class);
        kryo.register(Pawn[].class);
        kryo.register(Pawn[][].class);
        kryo.register(Player.class);
        kryo.register(TeamColor.class);
        kryo.register(PlayerID.class);
        kryo.register(Message.class);
    }

    /**
     * The enum Message.
     */
    public enum Message {
        /**
         * Room is full message.
         */
        ROOM_IS_FULL;

        /**
         * Instantiates a new Message.
         */
        Message() {
            // Used for kyro deserialization
        }
    }

    /**
     * The type Player id.
     */
    public static class PlayerID {
        /**
         * The Player id.
         */
        public int playerID;
        /**
         * The Team id.
         */
        public int teamID;

        /**
         * Instantiates a new Player id.
         */
        public PlayerID() {
            // Used for kyro deserialization
        }

        /**
         * Instantiates a new Player id.
         *
         * @param teamID   the team id
         * @param playerID the player id
         */
        public PlayerID(int teamID, int playerID) {
            this.teamID = teamID;
            this.playerID = playerID;
        }

        /**
         * Equals boolean.
         *
         * @param teamID   the team id
         * @param playerID the player id
         * @return the boolean
         */
        public boolean equals(int teamID, int playerID) {
            return this.teamID == teamID && this.playerID == playerID;
        }

        /**
         * To string string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return "PlayerID{" +
                    "playerID=" + this.playerID +
                    ", teamID=" + this.teamID +
                    '}';
        }
    }
}
