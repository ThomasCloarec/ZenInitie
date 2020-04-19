package view.sub_views.game_view.view_sections;

import controller.game.GameController;
import view.sub_views.Section;

import java.util.function.BooleanSupplier;

public class GameRightSection extends Section<GameController> {
    public GameRightSection(GameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
    }
}
