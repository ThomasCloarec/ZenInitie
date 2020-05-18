package network;

/**
 * The type Network.
 */
public final class Network {
    /**
     * The Base tcp port.
     */
    static final int BASE_TCP_PORT = 31415;
    /**
     * The Base udp port.
     */
    static final int BASE_UDP_PORT = 31425;
    /**
     * The Port search range.
     */
    static final int PORT_SEARCH_RANGE = 10;
    /**
     * The Max tcp port.
     */
    static final int MAX_TCP_PORT = Network.BASE_TCP_PORT + Network.PORT_SEARCH_RANGE - 1;
    /**
     * The Max udp port.
     */
    static final int MAX_UDP_PORT = Network.BASE_UDP_PORT + Network.PORT_SEARCH_RANGE - 1;

    /**
     * Instantiates a new Network.
     */
    private Network() {

    }
}
