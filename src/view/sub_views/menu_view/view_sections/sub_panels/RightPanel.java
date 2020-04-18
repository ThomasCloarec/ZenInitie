package view.sub_views.menu_view.view_sections.sub_panels;

import view.utils.AppColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class RightPanel extends SubPanel {
    public RightPanel() {
        Supplier<Point> center = () -> new Point((int) (this.getWidth() * 3.2), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent redLight = new LightComponent(center, radius, AppColor.CUSTOM_RED);
        redLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(redLight);

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        ImageComponent redDragon = new ScaledImageComponent("red_dragon.png", 0.75, 0.75, this);
        redDragon.setVisibleCondition(this.horizontalMode);
        this.add(redDragon);
        this.add(Box.createVerticalGlue());
    }
}