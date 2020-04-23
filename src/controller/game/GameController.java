package controller.game;

import model.game.Game;
import model.game.Position;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
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

        System.out.println("Selected Pawn : " + this.game.getBoard()[position.getLine()][position.getColumn()]);
        if (this.game.isPawnSelectable(position)) {
            this.game.setSelectedPawn(position);
        } else {
            responseError = ResponseError.SELECT_PAWN_ERROR;
        }

        return responseError;
    }

    public enum ResponseError {
        SELECT_PAWN_ERROR,
        MOVE_PAWN_ERROR
    }
}
