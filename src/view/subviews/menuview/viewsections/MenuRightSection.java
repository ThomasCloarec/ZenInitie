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
 * The type Menu right section.
 */
public class MenuRightSection extends Section<Graphic2DMenuController> {
    /**
     * Instantiates a new Menu right section.
     *
     * @param graphics2DMenuController the graphics 2 d menu controller
     * @param horizontalMode           the horizontal mode
     */
    public MenuRightSection(Graphic2DMenuController graphics2DMenuController, BooleanSupplier horizontalMode) {
        super(graphics2DMenuController, horizontalMode);

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * 3.2), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent redLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_RED);
        redLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(redLight);

        this.add(Box.createVerticalGlue());
        ImageComponent redDragon = new ScaledImageComponent("red_dragon.png", 0.75, 0.75, this);
        redDragon.setVisibleCondition(this.horizontalMode);
        this.add(redDragon);
        this.add(Box.createVerticalGlue());
    }
}
