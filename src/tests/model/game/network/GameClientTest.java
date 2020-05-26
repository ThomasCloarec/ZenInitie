package tests.model.game.network;

import model.game.Position;
import model.game.network.GameClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * The type Game network test.
 */
public class GameClientTest {
    /**
     * The Game network.
     */
    private GameClient game;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.game = new GameClient(false, false, () -> {
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
    public void testGameClientConstructor() {
        GameClient gameClient = new GameClient(false, false, () -> {
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
