package view.sub_views.game_view.view_sections;

import controller.game.GameController;
import view.sub_views.Section;

import javax.swing.*;
import java.util.function.BooleanSupplier;

public class GameContentSection extends Section<GameController> {
    public GameContentSection(GameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
        this.add(new GameToolbar(this::getWidth, () -> this.getWidth() / 4));
        this.add(Box.createVerticalGlue());
        this.add(new BoardPanel(gameController, this));
        this.add(Box.createVerticalGlue());
        this.add(Box.createVerticalGlue());
    }
}
