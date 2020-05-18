package view.subviews.menuview.viewsections;

import controller.menu.Graphic2DMenuController;
import view.subviews.Section;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.Box;
import java.awt.Point;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * The type Menu left section.
 */
public class MenuLeftSection extends Section<Graphic2DMenuController> {
    /**
     * Instantiates a new Menu left section.
     *
     * @param graphics2DMenuController the graphics 2 d menu controller
     * @param horizontalMode           the horizontal mode
     */
    public MenuLeftSection(Graphic2DMenuController graphics2DMenuController, BooleanSupplier horizontalMode) {
        super(graphics2DMenuController, horizontalMode);

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * -0.2), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent blueLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_BLUE);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(blueLight);

        this.add(Box.createVerticalGlue());
        ImageComponent blueDragon = new ScaledImageComponent("blue_dragon.png", 0.75, 0.75, this);
        blueDragon.setVisibleCondition(this.horizontalMode);
        this.add(blueDragon);
        this.add(Box.createVerticalGlue());
    }
}
