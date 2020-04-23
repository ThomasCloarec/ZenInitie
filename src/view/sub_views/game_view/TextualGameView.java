package view.sub_views.game_view;

import controller.game.GameController;
import model.game.Game;
import model.game.Pawn;
import model.game.Position;
import view.utils.text.TextInput;

import static view.utils.text.AppText.*;
import static view.utils.text.TextOutput.print;
import static view.utils.text.TextOutput.println;

public class TextualGameView implements GameView {
    private final GameController gameController;

    public TextualGameView(GameController gameController) {
        this.gameController = gameController;
    }

    public static void updateBoard(Pawn[][] board) {
        for (int i = 0; i < board.length + 4; i++) {
            print("▬\t");
        }
        print("\n▌\t\t");
        for (int characterColumn = 0; characterColumn < board[0].length; characterColumn++) {
            print((char) (65 + characterColumn) + "\t");
        }
        println("\t▐");
        for (int line = 0; line < board.length; line++) {
            print("▌\t" + (board.length - line) + "\t");
            for (int column = 0; column < board[0].length; column++) {
                if (board[line][column] == Pawn.BLACK) {
                    print("○");
                } else if (board[line][column] == Pawn.WHITE) {
                    print("●");
                } else if (board[line][column] == Pawn.ZEN) {
                    print("✪");
                } else {
                    print("·");
                }
                print("\t");
            }
            println((board.length - line) + "\t▐");
        }
        print("▌\t\t");
        for (int characterColumn = 0; characterColumn < board[0].length; characterColumn++) {
            print((char) (65 + characterColumn) + "\t");
        }
        println("\t▐");
        for (int i = 0; i < board.length + 4; i++) {
            print("▬\t");
        }
        println();
    }

    @Override
    public void movePawn(Game game) {
        println("Allowed moves : " + game.getAllowedMoves());
        Position position = TextInput.getMovePositionAnswer(game);
        if (this.gameController.movePawn(position) == GameController.ResponseError.MOVE_PAWN_ERROR) {
            println(preError + getTextFor("game.error.movePawn"));
            this.movePawn(game);
        }
    }

    @Override
    public void start(Game game) {
        this.selectPawn(game);
    }

    @Override
    public void selectPawn(Game game) {
        TextualGameView.updateBoard(game.getBoard());
        println(preInformation + "(" + game.getCurrentTeamName() + ") " + game.getCurrentPlayerName() + getTextFor("game.playerTurn"));
        Position position = TextInput.getSelectPositionAnswer(game);
        if (this.gameController.selectPawn(position) == GameController.ResponseError.SELECT_PAWN_ERROR) {
            println(preError + getTextFor("game.error.selectPawn"));
            this.selectPawn(game);
        }
    }
}
