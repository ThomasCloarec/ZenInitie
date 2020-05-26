package tests.model.game.board;

import model.game.board.Pawn;
import model.game.team.TeamColor;
import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * The type Pawn test.
 */
public class PawnTest {
    /**
     * Test get pawn from team color.
     */
    @Test
    public void testGetPawnFromTeamColor() {
        assertSame(Pawn.getPawnFromTeamColor(TeamColor.BLUE), Pawn.BLUE);
        assertSame(Pawn.getPawnFromTeamColor(TeamColor.RED), Pawn.RED);
    }
}
