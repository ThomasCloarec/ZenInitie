package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import view.subviews.Section;

import javax.swing.Box;
import java.util.function.BooleanSupplier;

public class GameContentSection extends Section<Graphic2DGameController> {
    public GameContentSection(Graphic2DGameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.add(Box.createVerticalGlue());
        this.add(new GameToolbar(gameController, this::getWidth, () -> (int) (this.getHeight() * (1.0d / 8.0d))));
        this.add(Box.createVerticalGlue());
        this.add(new BoardPanel(gameController, this));
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
    }
}
