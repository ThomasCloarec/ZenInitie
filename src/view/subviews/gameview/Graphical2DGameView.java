package view.subviews.gameview;

import controller.game.Graphic2DGameController;
import model.game.Game;
import view.subviews.CustomPanel;
import view.subviews.gameview.viewsections.GameContentSection;
import view.subviews.gameview.viewsections.GameLeftSection;
import view.subviews.gameview.viewsections.GameRightSection;

import javax.swing.SwingUtilities;

public class Graphical2DGameView extends CustomPanel<Graphic2DGameController, GameLeftSection, GameContentSection, GameRightSection> implements GameView {
    public Graphical2DGameView(Graphic2DGameController gameController) {
        super(gameController);

        SwingUtilities.invokeLater(() -> {
            this.rightSection = new GameRightSection(this.controller, this.isHorizontalMode);
            this.contentSection = new GameContentSection(this.controller, this.isHorizontalMode);
            this.leftSection = new GameLeftSection(this.controller, this.isHorizontalMode);
        });
    }

    @Override
    public void movePawn(Game game) {
        this.contentSection.movePawn(game);
    }

    @Override
    public void start(Game game) {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.start(game);
            this.leftSection.start(game);
            this.rightSection.start(game);
        });
    }

    @Override
    public void selectPawn(Game game) {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.selectPawn(game);
        });
    }
}
