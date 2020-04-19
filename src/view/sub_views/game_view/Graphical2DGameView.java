package view.sub_views.game_view;

import controller.game.GameController;
import model.game.Game;
import view.sub_views.game_view.view_sections.GameContentSection;
import view.sub_views.game_view.view_sections.GameLeftSection;
import view.sub_views.game_view.view_sections.GameRightSection;
import view.utils.components.CustomPanel;

import javax.swing.*;

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
