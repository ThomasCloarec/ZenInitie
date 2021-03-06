package view.subviews.gameview;

import controller.game.Graphic2DGameController;
import model.game.Game;
import model.game.team.Team;
import view.subviews.CustomPanel;
import view.subviews.gameview.viewsections.GameContentSection;
import view.subviews.gameview.viewsections.GameLeftSection;
import view.subviews.gameview.viewsections.GameRightSection;

import javax.swing.SwingUtilities;

/**
 * The type Graphical 2 d game view.
 */
public class Graphical2DGameView extends CustomPanel<Graphic2DGameController, GameLeftSection, GameContentSection, GameRightSection> implements GameView {
    /**
     * Instantiates a new Graphical 2 d game view.
     *
     * @param gameController the game controller
     */
    public Graphical2DGameView(Graphic2DGameController gameController) {
        super(gameController);

        SwingUtilities.invokeLater(() -> {
            this.rightSection = new GameRightSection(this.controller, this.isHorizontalMode);
            this.contentSection = new GameContentSection(this.controller, this.isHorizontalMode);
            this.leftSection = new GameLeftSection(this.controller, this.isHorizontalMode);
        });
    }

    /**
     * Move pawn.
     *
     * @param game the game
     */
    @Override
    public void movePawn(Game game) {
        this.contentSection.movePawn(game);
    }

    /**
     * Start.
     *
     * @param game the game
     */
    @Override
    public void start(Game game) {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.start(game);
            this.leftSection.start(game);
            this.rightSection.start(game);
        });
    }

    /**
     * Update game.
     *
     * @param game the game
     */
    @Override
    public void updateGame(Game game) {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.updateGame(game);
            this.leftSection.updateGame(game);
            this.rightSection.updateGame(game);
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Select pawn.
     *
     * @param game the game
     */
    @Override
    public void selectPawn(Game game) {
        SwingUtilities.invokeLater(() -> {
            this.leftSection.selectPawn(game);
            this.contentSection.selectPawn(game);
            this.rightSection.selectPawn(game);
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Game winner.
     *
     * @param team the team
     */
    @Override
    public void gameWinner(Team team) {
        SwingUtilities.invokeLater(() -> {
            this.leftSection.gameWinner(team);
            this.contentSection.gameWinner();
            this.rightSection.gameWinner(team);
            this.revalidate();
            this.repaint();
        });
    }
}
