package view.sub_view.graphical_2d;

import controller.game.GameController;
import view.sub_view.GameView;

import java.util.Observable;

public class Graphical2DGameView extends Graphical2DSubView implements GameView {
    private final GameController gameController;

    public Graphical2DGameView(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void start() {
    }

    @Override
    public void update(Observable observable, Object object) {
    }
}
