package view.subviews.gameview;

import controller.game.GameController;
import model.game.Game;
import view.subviews.CustomPanel;
import view.subviews.gameview.viewsections.GameContentSection;
import view.subviews.gameview.viewsections.GameLeftSection;
import view.subviews.gameview.viewsections.GameRightSection;

import javax.swing.SwingUtilities;

public class Graphical2DGameView extends CustomPanel<GameController, GameLeftSection, GameContentSection, GameRightSection> implements GameView {
    public Graphical2DGameView(GameController gameController) {
        super(gameController);

        SwingUtilities.invokeLater(() -> {
            this.rightSection = new GameRightSection(this.controller, this.isHorizontalMode);
            this.contentSection = new GameContentSection(this.controller, this.isHorizontalMode);
            this.leftSection = new GameLeftSection(this.controller, this.isHorizontalMode);
        });
    }

    @Override
    public void movePawn(Game game) {

    }

    @Override
    public void start(Game game) {

    }

    @Override
    public void selectPawn(Game game) {

    }

    @Override
    public void pawnMoved(Game game) {

    }
}
