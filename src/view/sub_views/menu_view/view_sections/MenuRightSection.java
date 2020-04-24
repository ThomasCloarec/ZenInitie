package view.sub_views.menu_view.view_sections;

import controller.menu.MenuController;
import view.sub_views.Section;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.*;
import java.awt.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class MenuRightSection extends Section<MenuController> {
    public MenuRightSection(MenuController menuController, BooleanSupplier horizontalMode) {
        super(menuController, horizontalMode);

        Supplier<Point> center = () -> new Point((int) (this.getWidth() * 3.2), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent redLight = new LightComponent(center, radius, ExtendedColor.CUSTOM_RED);
        redLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(redLight);

        this.add(Box.createVerticalGlue());
        ImageComponent redDragon = new ScaledImageComponent("red_dragon.gif", 0.75, 0.75, this);
        redDragon.setVisibleCondition(this.horizontalMode);
        this.add(redDragon);
        this.add(Box.createVerticalGlue());
    }
}
