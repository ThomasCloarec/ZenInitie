package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import model.game.Game;
import model.game.team.Team;
import view.subviews.Section;
import view.utils.components.ScaledImageComponent;

import javax.swing.Box;
import java.util.function.BooleanSupplier;

/**
 * The type Game content section.
 */
public class GameContentSection extends Section<Graphic2DGameController> {
    private final BoardPanel boardPanel;
    /**
     * The Game toolbar.
     */
    private final GameToolbar gameToolbar;

    /**
     * Instantiates a new Game content section.
     *
     * @param gameController the game controller
     * @param horizontalMode the horizontal mode
     */
    public GameContentSection(Graphic2DGameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.add(Box.createVerticalGlue());
        this.gameToolbar = new GameToolbar(gameController, this::getWidth, () -> (int) (this.getHeight() * (1.0d / 8.0d)));
        this.add(this.gameToolbar);
        this.add(Box.createVerticalGlue());
        this.boardPanel = new BoardPanel(gameController, this);
        this.add(this.boardPanel);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
    }

    /**
     * When a new game starts
     *
     * @param game the game
     */
    public void start(Game game) {
        this.boardPanel.start(game);
        this.gameToolbar.start();
    }

    /**
     * Select pawn.
     *
     * @param game the game
     */
    public void selectPawn(Game game) {
        this.boardPanel.selectPawn(game);
    }

    /**
     * Move pawn.
     *
     * @param game the game
     */
    public void movePawn(Game game) {
        this.boardPanel.movePawn(game);
    }

    /**
     * Game winner.
     *
     * @param team the team
     */
    public void gameWinner(Team team) {
        this.removeAll();
        this.add(Box.createVerticalGlue());
        this.add(this.gameToolbar);
        this.add(Box.createVerticalGlue());
        this.add(new ScaledImageComponent("finish.png", 0.6, 0.6, this));
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        this.doLayout();
        this.revalidate();
        this.repaint();
    }
}
