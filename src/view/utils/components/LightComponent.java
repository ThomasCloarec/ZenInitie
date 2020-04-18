package view.utils.components;

import view.utils.AppColor;

import java.awt.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class LightComponent {
    final Supplier<Point> center;
    final Color color;
    final Supplier<Float> radius;
    /**
     * The condition that returns if the image has to be visible or not
     */
    protected BooleanSupplier visibleCondition = () -> true;

    public LightComponent(Point center, float radius, Color color) {
        this(() -> center, () -> radius, color);
    }

    public LightComponent(Supplier<Point> center, Supplier<Float> radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public void paintLight(Graphics2D graphics2D) {
        if (this.visibleCondition.getAsBoolean()) {
            RadialGradientPaint light = new RadialGradientPaint(this.center.get(), this.radius.get(), new float[]{0f, 1f}, new Color[]{this.color, new AppColor(0, 0, 0, 0)});

            graphics2D.setPaint(light);
            graphics2D.fillOval(
                    (int) (light.getCenterPoint().getX() - light.getRadius()),
                    (int) (light.getCenterPoint().getY() - light.getRadius()),
                    (int) light.getRadius() * 2,
                    (int) light.getRadius() * 2
            );
        }
    }

    public void setVisibleCondition(BooleanSupplier visibleCondition) {
        this.visibleCondition = visibleCondition;
    }
}
