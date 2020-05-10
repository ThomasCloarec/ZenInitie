package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import model.game.Game;
import view.subviews.Section;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Point;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class GameLeftSection extends Section<Graphic2DGameController> {
    public GameLeftSection(Graphic2DGameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * -0.5), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 2.0f;
        LightComponent blueLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_BLUE);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(blueLight);
    }

    public void start(Game game) {
        String playerImagePath;
        if (game.isDuoMode()) {
            playerImagePath = "man2.png";
        } else {
            playerImagePath = "man.png";
        }

        ImageComponent blueDragon = new ScaledImageComponent("left_baner.png", 0.4, 0.9, this, false);
        blueDragon.setVisibleCondition(this.horizontalMode);
        this.add(blueDragon);
        this.add(Box.createHorizontalGlue());
        ImageComponent bluePlayer = new ScaledImageComponent(playerImagePath, 0.55, 0.55, this);
        bluePlayer.setVisibleCondition(this.horizontalMode);
        this.add(bluePlayer);
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
    }
}
