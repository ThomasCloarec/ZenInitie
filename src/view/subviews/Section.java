package view.subviews;

import view.utils.components.LightComponent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public abstract class Section<ControllerT> extends JPanel {
    protected final ControllerT controller;
    protected final BooleanSupplier horizontalMode;
    protected final ArrayList<LightComponent> lights = new ArrayList<>();

    protected Section(ControllerT controller, BooleanSupplier horizontalMode) {
        this.controller = controller;
        this.horizontalMode = horizontalMode;

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                Section.this.doLayout();
            }
        });
    }

    public void paintLights(Graphics2D graphics2D) {
        if (this.getWidth() != 0 && this.getHeight() != 0) {
            for (LightComponent light : this.lights) {
                light.paintLight(graphics2D);
            }
        }
    }
}
