package view.subviews;

import controller.Graphic2DController;
import view.utils.components.LightComponent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/**
 * The type Section.
 *
 * @param <ControllerT> the type parameter
 */
public abstract class Section<ControllerT> extends JPanel {
    /**
     * The Controller.
     */
    protected final ControllerT controller;
    /**
     * The Horizontal mode.
     */
    protected final BooleanSupplier horizontalMode;
    /**
     * The Lights.
     */
    protected final ArrayList<LightComponent> lights = new ArrayList<>();

    /**
     * Instantiates a new Section.
     *
     * @param controller     the controller
     * @param horizontalMode the horizontal mode
     */
    protected Section(ControllerT controller, BooleanSupplier horizontalMode) {
        this.controller = controller;
        this.horizontalMode = horizontalMode;

        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.addComponentListener(Graphic2DController.getResizeListener(this));
    }

    /**
     * Paint lights.
     *
     * @param graphics2D the graphics 2 d
     */
    public void paintLights(Graphics2D graphics2D) {
        if (this.getWidth() != 0 && this.getHeight() != 0) {
            for (LightComponent light : this.lights) {
                light.paintLight(graphics2D);
            }
        }
    }
}
