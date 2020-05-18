package utils.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import model.game.Board;
import model.game.GameData;
import model.game.Pawn;
import model.game.Position;
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
    public static final int PORT_SEARCH_RANGE = 10;
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
    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(GameData.class);
        kryo.register(ArrayList.class);
        kryo.register(Position.class);
        kryo.register(Board.class);
        kryo.register(List.class);
        kryo.register(Team.class);
        kryo.register(Pawn.class);
        kryo.register(Player.class);
        kryo.register(TeamColor.class);
    }
}
