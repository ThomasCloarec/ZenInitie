package view.utils.components;

import view.utils.ExtendedColor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * The type Light component.
 */
public class LightComponent {
    /**
     * The Center.
     */
    private Supplier<? extends Point> center;
    /**
     * The Color.
     */
    private final Color color;
    /**
     * The Radius.
     */
    private final Supplier<Float> radius;
    /**
     * The Visible.
     */
    private boolean visible = true;
    /**
     * The condition that returns if the image has to be visible or not
     */
    private BooleanSupplier visibleCondition = () -> true;

    /**
     * Instantiates a new Light component.
     *
     * @param center the center
     * @param radius the radius
     * @param color  the color
     */
    public LightComponent(Point center, float radius, Color color) {
        this(() -> center, () -> radius, color);
    }

    /**
     * Instantiates a new Light component.
     *
     * @param center the center
     * @param radius the radius
     * @param color  the color
     */
    public LightComponent(Supplier<? extends Point> center, Supplier<Float> radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * Paint light.
     *
     * @param graphics2D the graphics 2 d
     */
    public void paintLight(Graphics2D graphics2D) {
        if (this.visible && this.visibleCondition.getAsBoolean()) {
            RadialGradientPaint light = new RadialGradientPaint(this.center.get(), this.radius.get(), new float[]{0.0f, 1.0f}, new Color[]{this.color, ExtendedColor.TRANSPARENT});

            graphics2D.setPaint(light);
            Point2D centerPoint = light.getCenterPoint();
            graphics2D.fillOval(
                    (int) (centerPoint.getX() - light.getRadius()),
                    (int) (centerPoint.getY() - light.getRadius()),
                    (int) light.getRadius() * 2,
                    (int) light.getRadius() * 2
            );
        }
    }

    /**
     * Sets visible.
     *
     * @param value the value
     */
    public void setVisible(boolean value) {
        this.visible = value;
    }

    /**
     * Sets visible condition.
     *
     * @param visibleCondition the visible condition
     */
    public void setVisibleCondition(BooleanSupplier visibleCondition) {
        this.visibleCondition = visibleCondition;
    }

    /**
     * Sets center.
     *
     * @param center the center
     */
    public void setCenter(Supplier<? extends Point> center) {
        this.center = center;
    }
}
