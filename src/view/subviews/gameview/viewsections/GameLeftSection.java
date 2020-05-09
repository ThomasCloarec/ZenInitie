package view.subviews.gameview.viewsections;

import controller.game.GameController;
import view.subviews.Section;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.Box;
import java.awt.Component;
import java.awt.Point;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class GameLeftSection extends Section<GameController> {
    public GameLeftSection(GameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * -0.5), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 2.0f;
        LightComponent blueLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_BLUE);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(blueLight);

        this.add(Box.createVerticalGlue());
        ImageComponent blueDragon = new ScaledImageComponent("left_baner.png", 0.4, 0.9, this, false);
        blueDragon.setAlignmentX(Component.LEFT_ALIGNMENT);
        blueDragon.setVisibleCondition(this.horizontalMode);
        this.add(blueDragon);
        this.add(Box.createVerticalGlue());
    }
}
