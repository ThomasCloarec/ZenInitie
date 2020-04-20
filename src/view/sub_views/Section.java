package view.sub_views;

import view.utils.components.LightComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public abstract class Section<ControllerT> extends JPanel {
    protected final ArrayList<LightComponent> lights = new ArrayList<>();
    protected final BooleanSupplier horizontalMode;
    protected final ControllerT controller;

    public Section(ControllerT controller, BooleanSupplier horizontalMode) {
        this.controller = controller;
        this.horizontalMode = horizontalMode;

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void paintLights(Graphics2D graphics2D) {
        if (this.getWidth() != 0 && this.getHeight() != 0) {
            for (LightComponent light : this.lights) {
                light.paintLight(graphics2D);
            }
        }
    }
}
