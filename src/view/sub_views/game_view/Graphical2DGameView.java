package view.sub_views.game_view;

import controller.game.GameController;
import model.game.Game;

import javax.swing.*;

public class Graphical2DGameView extends JPanel implements GameView {
    private final GameController gameController;

    public Graphical2DGameView(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void movePawn(Game game) {

    }

    @Override
    public void pawnMoved(Game game) {

    }

    @Override
    public void selectPawn(Game game) {

    }

    @Override
    public void start(Game game) {

    }
}
