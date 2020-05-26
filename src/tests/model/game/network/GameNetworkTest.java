package tests.model.game.network;

import model.game.network.GameNetwork;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The type Game network test.
 */
public class GameNetworkTest {
    /**
     * The Game network.
     */
    private GameNetwork gameNetwork;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.gameNetwork = new GameNetwork(false, false, () -> {
        }, () -> {
        }) {
            @Override
            public void stop() {

            }
        };
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        this.gameNetwork = null;
    }

    /**
     * Test game network constructor.
     */
    @Test
    public void testGameNetworkConstructor() {
        GameNetwork gameNetwork = new GameNetwork(false, false, () -> {
        }, () -> {
        }) {
            @Override
            public void stop() {

            }
        };
    }
}
