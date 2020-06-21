package tests.model.game;

import model.game.Game;
import model.game.GameData;
import model.game.Position;
import model.game.board.Pawn;
import model.game.team.TeamColor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * The type Game test.
 */
public class GameTest {
    /**
     * The Game.
     */
    private Game game;

    /**
     * To execute before tests
     */
    @Before
    public void setUp() {
        this.game = new Game(false, false);
    }

    /**
     * To execute after tests
     */
    @After
    public void tearDown() {
        this.game = null;
    }

    /**
     * Test game constructor.
     */
    @Test
    public void testGameConstructor() {
        Game game1 = new Game(false, false);
        assertFalse(game1.isAiMode());
        assertFalse(game1.isDuoMode());

        Game game2 = new Game(false, true);
        assertFalse(game2.isAiMode());
        assertTrue(game2.isDuoMode());

        Game game3 = new Game(true, false);
        assertTrue(game3.isAiMode());
        assertFalse(game3.isDuoMode());

        Game game4 = new Game(true, true);
        assertTrue(game4.isAiMode());
        assertTrue(game4.isDuoMode());
    }

    /**
     * Test is move allowed.
     */
    @Test
    public void testIsMoveAllowed() {
        // Testing all moves on zen
        this.game.setSelectedPawn(new Position(5, 5));
        Position[] allowedMoves = {
                new Position(5, 8),
                new Position(5, 2),
                new Position(2, 5),
                new Position(8, 5),
                new Position(2, 2),
                new Position(2, 8),
                new Position(8, 8),
                new Position(8, 2)
        };

        for (int line = -100; line < 100; line++) {
            for (int column = -100; column < 100; column++) {
                Position position = new Position(line, column);
                this.game.isMoveAllowed(position);
                if (Arrays.asList(allowedMoves).contains(position)) {
                    assertTrue(this.game.isMoveAllowed(position));
                } else {
                    assertFalse(this.game.isMoveAllowed(position));
                }
            }
        }
    }

    /**
     * Test is pawn selectable.
     */
    @Test
    public void testIsPawnSelectable() {
        // testing on the zen
        assertTrue(this.game.isPawnSelectable(new Position(5, 5)));
        assertFalse(this.game.isPawnSelectable(new Position(-1, -1)));
        assertFalse(this.game.isPawnSelectable(new Position(11, 11)));

        // on empty cells
        assertFalse(this.game.isPawnSelectable(new Position(0, 1)));
        assertFalse(this.game.isPawnSelectable(new Position(0, 2)));
        assertFalse(this.game.isPawnSelectable(new Position(0, 3)));
        assertFalse(this.game.isPawnSelectable(new Position(0, 4)));
        assertFalse(this.game.isPawnSelectable(new Position(0, 6)));
        assertFalse(this.game.isPawnSelectable(new Position(0, 7)));
        assertFalse(this.game.isPawnSelectable(new Position(0, 8)));
        assertFalse(this.game.isPawnSelectable(new Position(0, 9)));

        assertFalse(this.game.isPawnSelectable(new Position(1, 0)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 1)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 2)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 3)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 5)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 7)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 8)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 9)));
        assertFalse(this.game.isPawnSelectable(new Position(1, 10)));

        assertFalse(this.game.isPawnSelectable(new Position(2, 0)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 1)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 2)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 4)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 5)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 6)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 8)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 9)));
        assertFalse(this.game.isPawnSelectable(new Position(2, 10)));

        assertFalse(this.game.isPawnSelectable(new Position(3, 0)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 1)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 3)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 4)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 5)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 6)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 7)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 9)));
        assertFalse(this.game.isPawnSelectable(new Position(3, 10)));

        assertFalse(this.game.isPawnSelectable(new Position(4, 0)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 2)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 3)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 4)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 5)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 6)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 7)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 8)));
        assertFalse(this.game.isPawnSelectable(new Position(4, 10)));

        assertFalse(this.game.isPawnSelectable(new Position(5, 1)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 2)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 3)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 4)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 6)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 7)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 8)));
        assertFalse(this.game.isPawnSelectable(new Position(5, 9)));

        // On pawns from teams
        if (this.game.getCurrentTeam().getTeamColor() == TeamColor.BLUE) {
            assertTrue(this.game.isPawnSelectable(Pawn.BLUE));
            assertFalse(this.game.isPawnSelectable(new Position(0, 0)));
            assertFalse(this.game.isPawnSelectable(new Position(5, 0)));
            assertFalse(this.game.isPawnSelectable(new Position(5, 10)));
            assertFalse(this.game.isPawnSelectable(new Position(10, 10)));
            assertTrue(this.game.isPawnSelectable(new Position(0, 5)));
            assertTrue(this.game.isPawnSelectable(new Position(0, 10)));
            assertTrue(this.game.isPawnSelectable(new Position(10, 0)));

            this.game.setSelectedPawn(new Position(5, 5));
            this.game.moveSelectedPawn(new Position(8, 5));

            assertTrue(this.game.isPawnSelectable(new Position(0, 0)));
            assertTrue(this.game.isPawnSelectable(new Position(5, 0)));
            assertTrue(this.game.isPawnSelectable(new Position(5, 10)));
            assertTrue(this.game.isPawnSelectable(new Position(10, 10)));
            assertFalse(this.game.isPawnSelectable(new Position(0, 5)));
            assertFalse(this.game.isPawnSelectable(new Position(0, 10)));
            assertFalse(this.game.isPawnSelectable(new Position(10, 0)));
        } else {
            assertTrue(this.game.isPawnSelectable(Pawn.RED));
            assertTrue(this.game.isPawnSelectable(new Position(0, 0)));
            assertTrue(this.game.isPawnSelectable(new Position(5, 0)));
            assertTrue(this.game.isPawnSelectable(new Position(5, 10)));
            assertTrue(this.game.isPawnSelectable(new Position(10, 10)));
            assertFalse(this.game.isPawnSelectable(new Position(0, 5)));
            assertFalse(this.game.isPawnSelectable(new Position(0, 10)));
            assertFalse(this.game.isPawnSelectable(new Position(10, 0)));

            this.game.setSelectedPawn(new Position(5, 5));
            this.game.moveSelectedPawn(new Position(8, 5));

            assertFalse(this.game.isPawnSelectable(new Position(0, 0)));
            assertFalse(this.game.isPawnSelectable(new Position(5, 0)));
            assertFalse(this.game.isPawnSelectable(new Position(5, 10)));
            assertFalse(this.game.isPawnSelectable(new Position(10, 10)));
            assertTrue(this.game.isPawnSelectable(new Position(0, 5)));
            assertTrue(this.game.isPawnSelectable(new Position(0, 10)));
            assertTrue(this.game.isPawnSelectable(new Position(10, 0)));
        }
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

    /**
     * Test set game data.
     */
    @Test
    public void testSetGameData() {
        GameData gameData = new GameData();
        this.game.setGameData(gameData);
    }

    /**
     * Test get allowed moves.
     */
    @Test
    public void testGetAllowedMoves() {
        this.game.setSelectedPawn(new Position(5, 5));

        assertNotNull(this.game.getAllowedMoves());

        assertTrue(this.game.getAllowedMoves().contains(new Position(8, 5)));
        assertTrue(this.game.getAllowedMoves().contains(new Position(2, 5)));
        assertTrue(this.game.getAllowedMoves().contains(new Position(2, 2)));
        assertTrue(this.game.getAllowedMoves().contains(new Position(2, 8)));
        assertTrue(this.game.getAllowedMoves().contains(new Position(8, 8)));
        assertTrue(this.game.getAllowedMoves().contains(new Position(8, 2)));
        assertTrue(this.game.getAllowedMoves().contains(new Position(5, 2)));
        assertTrue(this.game.getAllowedMoves().contains(new Position(5, 8)));

        assertFalse(this.game.getAllowedMoves().contains(new Position(-1, -1)));
        assertFalse(this.game.getAllowedMoves().contains(new Position(11, 11)));
    }

    /**
     * Test get board array.
     */
    @Test
    public void testGetBoardArray() {
        assertNotNull(this.game.getBoardArray());
    }

    /**
     * Test get board size.
     */
    @Test
    public void testGetBoardSize() {
        assertTrue(this.game.getBoardSize() >= 0);
    }

    /**
     * Test get current player name.
     */
    @Test
    public void testGetCurrentPlayerName() {
        assertNotNull(this.game.getCurrentPlayerName());
    }

    /**
     * Test get current team.
     */
    @Test
    public void testGetCurrentTeam() {
        assertNotNull(this.game.getCurrentTeam());
        TeamColor teamColor = this.game.getCurrentTeam().getTeamColor();
        this.game.setSelectedPawn(new Position(5, 5));
        this.game.moveSelectedPawn(new Position(8, 5));
        assertNotSame(this.game.getCurrentTeam().getTeamColor(), teamColor);
    }
}