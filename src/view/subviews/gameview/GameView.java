package view.subviews.gameview;

import model.game.Game;
import utils.observer.Observer;

public interface GameView extends Observer<GameView> {
    void movePawn(Game game);

    void start(Game game);

    void selectPawn(Game game);

    default void pawnMoved(Game game) {
        this.selectPawn(game);
    }

    default void pawnSelected(Game game) {
        this.movePawn(game);
    }
}
