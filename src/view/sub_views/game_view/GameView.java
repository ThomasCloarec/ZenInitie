package view.sub_views.game_view;

import model.game.Game;

public interface GameView {
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
