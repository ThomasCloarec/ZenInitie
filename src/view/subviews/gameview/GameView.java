package view.subviews.gameview;

import model.game.Game;
import model.game.team.Team;
import utils.observer.Observer;

/**
 * The interface Game view.
 */
public interface GameView extends Observer<GameView> {
    /**
     * Move pawn.
     *
     * @param game the game
     */
    void movePawn(Game game);

    /**
     * Start.
     *
     * @param game the game
     */
    void start(Game game);

    /**
     * Select pawn.
     *
     * @param game the game
     */
    void selectPawn(Game game);

    /**
     * Game winner.
     *
     * @param team the team
     */
    void gameWinner(Team team);

    /**
     * Pawn moved.
     *
     * @param game the game
     */
    default void pawnMoved(Game game) {
        this.selectPawn(game);
    }

    /**
     * Pawn selected.
     *
     * @param game the game
     */
    default void pawnSelected(Game game) {
        this.movePawn(game);
    }
}
