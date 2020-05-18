package controller.game;

import model.game.Game;
import model.game.Position;
import model.game.team.TeamColor;

import java.util.List;

public class GameController {
    private final Game game;
    private final Runnable goMenu;

    public GameController(Game game, Runnable goMenu) {
        this.game = game;
        this.goMenu = goMenu;
    }

    public ResponseError movePawn(Position position) {
        ResponseError responseError = null;

        if (this.game.isMoveAllowed(position)) {
            this.game.moveSelectedPawn(position);
        } else {
            responseError = ResponseError.MOVE_PAWN_ERROR;
        }

        return responseError;
    }

    public ResponseError selectPawn(Position position) {
        ResponseError responseError = null;

        if (this.game.isPawnSelectable(position)) {
            this.game.setSelectedPawn(position);
        } else {
            responseError = ResponseError.SELECT_PAWN_ERROR;
        }

        return responseError;
    }

    public void goMenu() {
        this.goMenu.run();
    }

    public List<Position> getAllowedMoves() {
        return this.game.getAllowedMoves();
    }

    public boolean isMovingPawn() {
        return this.game.isMovingPawn();
    }

    public boolean isCurrentTeamPawn(int line, int column) {
        return this.game.isPawnSelectable(new Position(line, column));
    }

    public TeamColor getCurrentTeamColor() {
        return this.game.getCurrentTeam().getTeamColor();
    }
}
