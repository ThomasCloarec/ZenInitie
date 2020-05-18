package view.subviews.gameview;

import controller.game.GameController;
import controller.game.ResponseError;
import model.game.Game;
import model.game.Pawn;
import model.game.Position;
import model.game.team.Team;
import view.utils.text.AppText;
import view.utils.text.TextInput;

/**
 * The type Textual game view.
 */
public class TextualGameView implements GameView {
    /**
     * The Game controller.
     */
    private final GameController gameController;

    /**
     * Instantiates a new Textual game view.
     *
     * @param gameController the game controller
     */
    public TextualGameView(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Update board.
     *
     * @param board the board
     */
    public static void updateBoard(Pawn[][] board) {
        for (int i = 0; i < board.length + 4; i++) {
            System.out.print("═\t");
        }
        System.out.print("\n║\t\t");
        for (int characterColumn = 0; characterColumn < board[0].length; characterColumn++) {
            System.out.print((char) (65 + characterColumn) + "\t");
        }
        System.out.println("\t║");
        for (int line = 0; line < board.length; line++) {
            System.out.print("║\t" + (board.length - line) + "\t");
            for (int column = 0; column < board[0].length; column++) {
                if (board[line][column] == Pawn.BLUE) {
                    System.out.print("o");
                } else if (board[line][column] == Pawn.RED) {
                    System.out.print("*");
                } else if (board[line][column] == Pawn.ZEN) {
                    System.out.print("+");
                } else {
                    System.out.print("·");
                }
                System.out.print("\t");
            }
            System.out.println((board.length - line) + "\t║");
        }
        System.out.print("║\t\t");
        for (int characterColumn = 0; characterColumn < board[0].length; characterColumn++) {
            System.out.print((char) (65 + characterColumn) + "\t");
        }
        System.out.println("\t║");
        for (int i = 0; i < board.length + 4; i++) {
            System.out.print("═\t");
        }
        System.out.println();
    }

    /**
     * Move pawn.
     *
     * @param game the game
     */
    @Override
    public void movePawn(Game game) {
        System.out.println("Allowed moves : " + game.getAllowedMoves());
        Position position = TextInput.getMovePositionAnswer(game);
        if (this.gameController.movePawn(position) == ResponseError.MOVE_PAWN_ERROR) {
            System.out.println(AppText.preError + AppText.getTextFor("game.error.movePawn"));
            this.movePawn(game);
        }
    }

    /**
     * Start.
     *
     * @param game the game
     */
    @Override
    public void start(Game game) {
        this.selectPawn(game);
    }

    /**
     * Select pawn.
     *
     * @param game the game
     */
    @Override
    public void selectPawn(Game game) {
        TextualGameView.updateBoard(game.getBoardArray());
        System.out.println(AppText.preInformation + "(" + game.getCurrentTeam().getName() + ") " + game.getCurrentPlayerName() + AppText.getTextFor("game.playerTurn"));
        Position position = TextInput.getSelectPositionAnswer(game);
        if (this.gameController.selectPawn(position) == ResponseError.SELECT_PAWN_ERROR) {
            System.out.println(AppText.preError + AppText.getTextFor("game.error.selectPawn"));
            this.selectPawn(game);
        }
    }

    /**
     * Game winner.
     *
     * @param team the team
     */
    @Override
    public void gameWinner(Team team) {
        System.out.println("------------------------");
        System.out.println(team == null ? AppText.getTextFor("game.draw") : team.getName() + AppText.getTextFor("game.win"));
        System.out.println("------------------------");
        this.gameController.goMenu();
    }
}
