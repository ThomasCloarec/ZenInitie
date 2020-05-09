package view.utils.components;

import view.utils.ExtendedColor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class LightComponent {
    private final Supplier<? extends Point> center;
    private final Color color;
    private final Supplier<Float> radius;
    /**
     * The condition that returns if the image has to be visible or not
     */
    private BooleanSupplier visibleCondition = () -> true;

    public LightComponent(Point center, float radius, Color color) {
        this(() -> center, () -> radius, color);
    }

    public LightComponent(Supplier<? extends Point> center, Supplier<Float> radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public void paintLight(Graphics2D graphics2D) {
        if (this.visibleCondition.getAsBoolean()) {
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

    public void setVisibleCondition(BooleanSupplier visibleCondition) {
        this.visibleCondition = visibleCondition;
    }
}
