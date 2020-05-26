package tests.model.game;

import model.game.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The type Position test.
 */
public class PositionTest {
    /**
     * The Position.
     */
    private Position position;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.position = new Position(5, 5);
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        this.position = null;
    }

    /**
     * Test position constructor.
     */
    @Test
    public void testPositionConstructor() {
        for (int line = -100; line < 100; line++) {
            for (int column = -100; column < 100; column++) {
                Position position = new Position(line, column);
                assertEquals(position.getLine(), line);
                assertEquals(position.getColumn(), column);
            }
        }
    }

    /**
     * Test get formatted line.
     */
    @Test
    public void testGetFormattedLine() {
        assertEquals(this.position.getFormattedLine(), 6);
    }

    /**
     * Test get formatted column.
     */
    @Test
    public void testGetFormattedColumn() {
        assertEquals(this.position.getFormattedColumn(), 'E');
    }
}
