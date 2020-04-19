package view.sub_views.game_view;

import controller.game.GameController;
import model.game.Game;
import view.sub_views.game_view.view_sections.GameContentSection;
import view.sub_views.game_view.view_sections.GameLeftSection;
import view.sub_views.game_view.view_sections.GameRightSection;

import javax.swing.*;
import java.util.function.BooleanSupplier;

public class Graphical2DGameView extends JPanel implements GameView {
    private final BooleanSupplier isHorizontalMode = () -> this.getWidth() > this.getHeight();
    private final GameController gameController;
    private GameContentSection contentSection;
    private GameLeftSection leftSection;
    private GameRightSection rightSection;

    public Graphical2DGameView(GameController gameController) {
        this.gameController = gameController;

        SwingUtilities.invokeLater(() -> {
            this.setOpaque(false);

            this.rightSection = new GameRightSection(this.gameController, this.isHorizontalMode);
            this.contentSection = new GameContentSection(this.gameController, this.isHorizontalMode);
            this.leftSection = new GameLeftSection(this.gameController, this.isHorizontalMode);
        });
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
