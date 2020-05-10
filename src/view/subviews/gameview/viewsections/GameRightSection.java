package view.subviews.gameview.viewsections;

import controller.game.Graphic2DGameController;
import view.subviews.Section;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Point;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class GameRightSection extends Section<Graphic2DGameController> {
    public GameRightSection(Graphic2DGameController gameController, BooleanSupplier horizontalMode) {
        super(gameController, horizontalMode);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * 3.5), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 2.0f;
        LightComponent blueLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_RED);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(blueLight);

        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        this.add(Box.createHorizontalGlue());
        ImageComponent chineseGirl = new ScaledImageComponent("woman2.png", 0.55, 0.55, this);
        chineseGirl.setVisibleCondition(this.horizontalMode);
        this.add(chineseGirl);

        this.add(Box.createHorizontalGlue());
        ImageComponent blueDragon = new ScaledImageComponent("right_baner.png", 0.4, 0.9, this, false);
        blueDragon.setVisibleCondition(this.horizontalMode);
        this.add(blueDragon);

        Supplier<Point> girlCenter = () -> new Point(chineseGirl.getX() + this.getWidth() * 2 + chineseGirl.getWidth() / 2, chineseGirl.getY() + chineseGirl.getHeight() / 2);
        Supplier<Float> girlRadius = () -> chineseGirl.getHeight() * 1.5f;
        LightComponent girlLight = new LightComponent(girlCenter, girlRadius, Color.GRAY);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(girlLight);
    }
}
