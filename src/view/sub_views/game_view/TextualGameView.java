package view.sub_views.game_view;

import controller.game.GameController;
import model.game.Game;
import model.game.Pawn;
import model.game.Position;
import view.utils.text.TextInput;

import static view.utils.text.AppText.*;

public class TextualGameView implements GameView {
    private final GameController gameController;

    public TextualGameView(GameController gameController) {
        this.gameController = gameController;
    }

    public static void updateBoard(Pawn[][] board) {
        for (int i = 0; i < board.length + 4; i++) {
            System.out.print("▬\t");
        }
        System.out.print("\n▌\t\t");
        for (int characterColumn = 0; characterColumn < board[0].length; characterColumn++) {
            System.out.print((char) (65 + characterColumn) + "\t");
        }
        System.out.println("\t▐");
        for (int line = 0; line < board.length; line++) {
            System.out.print("▌\t" + (board.length - line) + "\t");
            for (int column = 0; column < board[0].length; column++) {
                if (board[line][column] == Pawn.BLACK) {
                    System.out.print("○");
                } else if (board[line][column] == Pawn.WHITE) {
                    System.out.print("●");
                } else if (board[line][column] == Pawn.ZEN) {
                    System.out.print("✪");
                } else {
                    System.out.print("·");
                }
                System.out.print("\t");
            }
            System.out.println((board.length - line) + "\t▐");
        }
        System.out.print("▌\t\t");
        for (int characterColumn = 0; characterColumn < board[0].length; characterColumn++) {
            System.out.print((char) (65 + characterColumn) + "\t");
        }
        System.out.println("\t▐");
        for (int i = 0; i < board.length + 4; i++) {
            System.out.print("▬\t");
        }
        System.out.println();
    }

    @Override
    public void movePawn(Game game) {
        System.out.println("Allowed moves : " + game.getAllowedMoves());
        Position position = TextInput.getMovePositionAnswer(game);
        if (this.gameController.movePawn(position) == GameController.ResponseError.MOVE_PAWN_ERROR) {
            System.out.println(preError + getTextFor("game.error.movePawn"));
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
        System.out.println(preInformation + "(" + game.getCurrentTeamName() + ") " + game.getCurrentPlayerName() + getTextFor("game.playerTurn"));
        Position position = TextInput.getSelectPositionAnswer(game);
        if (this.gameController.selectPawn(position) == GameController.ResponseError.SELECT_PAWN_ERROR) {
            System.out.println(preError + getTextFor("game.error.selectPawn"));
            this.selectPawn(game);
        }
    }
}
