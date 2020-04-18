package view.sub_views.menu_view.view_sections.sub_panels;

import view.utils.AppColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class LeftPanel extends SubPanel {
    public LeftPanel() {
        Supplier<Point> center = () -> new Point((int) (this.getWidth() * -0.2), this.getHeight() / 2);
        Supplier<Float> radius = () -> this.getWidth() * 1.5f;
        LightComponent blueLight = new LightComponent(center, radius, AppColor.CUSTOM_BLUE);
        blueLight.setVisibleCondition(this.horizontalMode);
        this.lights.add(blueLight);

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        ImageComponent blueDragon = new ScaledImageComponent("blue_dragon.png", 0.75, 0.75, this);
        blueDragon.setVisibleCondition(this.horizontalMode);
        this.add(blueDragon);
        this.add(Box.createVerticalGlue());
    }
}
