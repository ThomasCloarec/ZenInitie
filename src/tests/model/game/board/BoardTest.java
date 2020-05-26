package tests.model.game.board;

import model.game.Position;
import model.game.board.Board;
import model.game.board.Pawn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The type Board test.
 */
public class BoardTest {
    /**
     * The Board.
     */
    private Board board;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.board = new Board();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        this.board = null;
    }

    /**
     * Test board constructor.
     */
    @Test
    public void testBoardConstructor() {
        Board board = new Board();
    }

    /**
     * Test get pawn positions around.
     */
    @Test
    public void testGetPawnPositionsAround() {
        assertNotNull(this.board.getPawnPositionsAround(Pawn.ZEN));
        assertNotNull(this.board.getPawnPositionsAround(Pawn.RED));
        assertNotNull(this.board.getPawnPositionsAround(Pawn.RED));
    }

    /**
     * Test generate positions from.
     */
    @Test
    public void testGeneratePositionsFrom() {
        for (int line = 0; line < 11; line++) {
            for (int column = 0; column < 11; column++) {
                Position position = new Position(line, column);
                assertNotNull(this.board.generatePositionsFrom(position));
            }
        }
    }

    /**
     * Test is position valid.
     */
    @Test
    public void testIsPositionValid() {
        assertFalse(this.board.isPositionValid(new Position(-1, -1)));
        assertFalse(this.board.isPositionValid(new Position(11, 11)));
        for (int line = 0; line < 11; line++) {
            for (int column = 0; column < 11; column++) {
                assertTrue(this.board.isPositionValid(new Position(line, column)));
            }
        }
    }

    /**
     * Test get array.
     */
    @Test
    public void testGetArray() {
        assertNotNull(this.board.getArray());
    }
}
