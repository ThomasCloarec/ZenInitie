package view.sub_views.game_view.view_sections;

import controller.game.GameController;
import view.utils.components.Section;

import java.util.function.BooleanSupplier;

public class GameLeftSection extends Section<GameController> {
    public GameLeftSection(GameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
    }
}
