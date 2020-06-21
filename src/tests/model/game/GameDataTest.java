package tests.model.game;

import model.game.GameData;
import model.game.Position;
import model.game.team.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The type Game data test.
 */
public class GameDataTest {
    /**
     * The Game data.
     */
    private GameData gameData;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.gameData = new GameData();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        this.gameData = null;
    }

    /**
     * Test game data constructor.
     */
    @Test
    public void testGameDataConstructor() {
        GameData gameData = new GameData();
    }

    /**
     * Test get board.
     */
    @Test
    public void testGetBoard() {
        assertNotNull(this.gameData.getBoard());
    }

    /**
     * Test set selected pawn.
     */
    @Test
    public void testSetSelectedPawn() {
        Position position = new Position(5, 5);
        this.gameData.setSelectedPawn(position);
        assertEquals(this.gameData.getSelectedPawn(), position);
    }

    /**
     * Test set winner.
     */
    @Test
    public void testSetWinner() {
        Team winner = new Team();
        this.gameData.setWinner(winner);
        assertEquals(this.gameData.getWinner(), winner);
    }
}
