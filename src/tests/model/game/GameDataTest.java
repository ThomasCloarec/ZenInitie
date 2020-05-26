package tests.model.game;

import model.game.GameData;
import model.game.Position;
import model.game.team.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
     * Test get allowed moves.
     */
    @Test
    public void testGetAllowedMoves() {
        this.gameData.setSelectedPawn(new Position(5, 5));

        assertNotNull(this.gameData.getAllowedMoves());

        assertTrue(this.gameData.getAllowedMoves().contains(new Position(8, 5)));
        assertTrue(this.gameData.getAllowedMoves().contains(new Position(2, 5)));
        assertTrue(this.gameData.getAllowedMoves().contains(new Position(2, 2)));
        assertTrue(this.gameData.getAllowedMoves().contains(new Position(2, 8)));
        assertTrue(this.gameData.getAllowedMoves().contains(new Position(8, 8)));
        assertTrue(this.gameData.getAllowedMoves().contains(new Position(8, 2)));
        assertTrue(this.gameData.getAllowedMoves().contains(new Position(5, 2)));
        assertTrue(this.gameData.getAllowedMoves().contains(new Position(5, 8)));

        assertFalse(this.gameData.getAllowedMoves().contains(new Position(-1, -1)));
        assertFalse(this.gameData.getAllowedMoves().contains(new Position(11, 11)));
    }

    /**
     * Test get board.
     */
    @Test
    public void testGetBoard() {
        assertNotNull(this.gameData.getBoard());
    }

    /**
     * Test get selected pawn.
     */
    @Test
    public void testGetSelectedPawn() {
        assertNotNull(this.gameData.getSelectedPawn());
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
     * Test get winner.
     */
    @Test
    public void testGetWinner() {
        assertNotNull(this.gameData.getWinner());
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
