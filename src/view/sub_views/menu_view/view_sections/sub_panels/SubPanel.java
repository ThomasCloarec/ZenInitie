package view.sub_views.menu_view.view_sections.sub_panels;

import view.utils.components.LightComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public abstract class SubPanel extends JPanel {
    protected final ArrayList<LightComponent> lights = new ArrayList<>();
    protected BooleanSupplier horizontalMode = () -> true;

    public void paintLights(Graphics2D graphics2D) {
        for (LightComponent light : this.lights) {
            light.paintLight(graphics2D);
        }
        this.revalidate();
    }

    public void setHorizontalMode(BooleanSupplier horizontalMode) {
        this.horizontalMode = horizontalMode;
    }
}
