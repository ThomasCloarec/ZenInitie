package tests.model.game.network;

import model.game.Position;
import model.game.network.GameServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The type Game network test.
 */
public class GameServerTest {
    /**
     * The Game network.
     */
    private GameServer game;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.game = new GameServer(false, false, () -> {
        }, () -> {
        });
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        this.game = null;
    }

    /**
     * Test game network constructor.
     */
    @Test
    public void testGameServerConstructor() {
        GameServer gameServer = new GameServer(false, false, () -> {
        }, () -> {
        });
    }

    /**
     * Test move selected pawn.
     */
    @Test
    public void testMoveSelectedPawn() {
        assertFalse(this.game.isPawnSelectable(new Position(8, 5)));
        assertTrue(this.game.isPawnSelectable(new Position(5, 5)));

        this.game.setSelectedPawn(new Position(5, 5));
        this.game.moveSelectedPawn(new Position(8, 5));

        assertTrue(this.game.isPawnSelectable(new Position(8, 5)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 5)));
    }
}
