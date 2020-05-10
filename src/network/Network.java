package network;

public final class Network {
    static final int BASE_TCP_PORT = 31415;
    static final int BASE_UDP_PORT = 31425;
    static final int PORT_SEARCH_RANGE = 10;
    static final int MAX_TCP_PORT = Network.BASE_TCP_PORT + Network.PORT_SEARCH_RANGE - 1;
    static final int MAX_UDP_PORT = Network.BASE_UDP_PORT + Network.PORT_SEARCH_RANGE - 1;

    private Network() {

    }
}
