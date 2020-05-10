package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import model.game.Game;
import view.subviews.Section;

import javax.swing.Box;
import java.util.function.BooleanSupplier;

public class GameContentSection extends Section<Graphic2DGameController> {
    private final BoardPanel boardPanel;
    private final GameToolbar gameToolbar;

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

    public void start(Game game) {
        this.boardPanel.start(game);
        this.gameToolbar.start();
    }
}
