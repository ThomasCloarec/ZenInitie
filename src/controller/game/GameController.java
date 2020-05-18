package controller.game;

import model.game.Game;
import model.game.Position;
import model.game.team.TeamColor;

import java.util.List;

/**
 * The type Game controller.
 */
public class GameController {
    /**
     * The Game.
     */
    private final Game game;
    /**
     * The Go menu.
     */
    private final Runnable goMenu;

    /**
     * Instantiates a new Game controller.
     *
     * @param game   the game
     * @param goMenu the go menu
     */
    public GameController(Game game, Runnable goMenu) {
        this.game = game;
        this.goMenu = goMenu;
    }

    /**
     * Move pawn response error.
     *
     * @param position the position
     * @return the response error
     */
    public ResponseError movePawn(Position position) {
        ResponseError responseError = null;

        if (this.game.isMoveAllowed(position)) {
            this.game.moveSelectedPawn(position);
        } else {
            responseError = ResponseError.MOVE_PAWN_ERROR;
        }

        return responseError;
    }

    /**
     * Select pawn response error.
     *
     * @param position the position
     * @return the response error
     */
    public ResponseError selectPawn(Position position) {
        ResponseError responseError = null;

        if (this.game.isPawnSelectable(position)) {
            this.game.setSelectedPawn(position);
        } else {
            responseError = ResponseError.SELECT_PAWN_ERROR;
        }

        return responseError;
    }

    /**
     * Go menu.
     */
    public void goMenu() {
        this.goMenu.run();
    }

    /**
     * Is current team pawn boolean.
     *
     * @param line   the line
     * @param column the column
     * @return the boolean
     */
    public boolean isCurrentTeamPawn(int line, int column) {
        return this.game.isPawnSelectable(new Position(line, column));
    }

    /**
     * Gets allowed moves.
     *
     * @return the allowed moves
     */
    public List<Position> getAllowedMoves() {
        return this.game.getAllowedMoves();
    }

    /**
     * Gets current team color.
     *
     * @return the current team color
     */
    public TeamColor getCurrentTeamColor() {
        return this.game.getCurrentTeam().getTeamColor();
    }

    /**
     * Is moving pawn boolean.
     *
     * @return the boolean
     */
    public boolean isMovingPawn() {
        return this.game.isMovingPawn();
    }
}
