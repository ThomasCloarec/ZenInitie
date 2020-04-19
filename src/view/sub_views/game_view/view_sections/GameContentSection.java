package view.sub_views.game_view.view_sections;

import controller.game.GameController;
import view.utils.components.ButtonComponent;
import view.utils.components.Section;

import java.util.function.BooleanSupplier;

public class GameContentSection extends Section<GameController> {
    public GameContentSection(GameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);

        this.add(new ButtonComponent("Hello", this, horizontalMode));
    }
}
