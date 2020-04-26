package view.utils.components;

import java.awt.*;
import java.util.function.Supplier;

public class ScaledImageComponent extends ImageComponent {
    private final Supplier<Double> heightScalar;
    private final boolean keepRatio;
    private final Component referenceComponent;
    private final Supplier<Double> widthScalar;
    private boolean squared;

    public ScaledImageComponent(String name, Supplier<Double> widthScalar, Supplier<Double> heightScalar, Component referenceComponent, boolean keepRatio) {
        super(name);

        this.referenceComponent = referenceComponent;
        this.widthScalar = widthScalar;
        this.heightScalar = heightScalar;
        this.keepRatio = keepRatio;
    }

    /**
     * ScaledImageConstructor scaled by width and height relative to referenceComponent.
     * If the <i>keepRatio</i> is true, the image will try to be as big as possible but will respect the following constraints :
     * <ul>
     *     <li>Stay lower than a width percentage of the screen (calculated as <i>widthScalar</i> * <i>referenceComponent.getWidth()</i>)</li>
     *     <li>Stay lower than a height percentage of the screen (calculated as <i>heightScalar</i> * <i>referenceComponent.getHeight()</i>)</li>
     * </ul>
     *
     * @param name               name of the image
     * @param widthScalar        the widthScalar of the image
     * @param heightScalar       the heightScalar of the image
     * @param referenceComponent referenceComponent used to scale the image
     * @param keepRatio          keep the ratio of the image or not
     */
    public ScaledImageComponent(String name, double widthScalar, double heightScalar, Component referenceComponent, boolean keepRatio) {
        this(name, () -> widthScalar, () -> heightScalar, referenceComponent, keepRatio);
    }

    public ScaledImageComponent(String name, Supplier<Double> widthScalar, Supplier<Double> heightScalar, Component referenceComponent) {
        this(name, widthScalar, heightScalar, referenceComponent, true);
    }

    /**
     * ScaledImageConstructor scaled by width and height relative to referenceComponent.
     * The image try to be as big as possible but respect the following constraints :
     * <ul>
     *     <li>Stay lower than a width percentage of the screen (calculated as <i>widthScalar</i> * <i>referenceComponent.getWidth()</i>)</li>
     *     <li>Stay lower than a height percentage of the screen (calculated as <i>heightScalar</i> * <i>referenceComponent.getHeight()</i>)</li>
     * </ul>
     *
     * @param name               name of the image
     * @param widthScalar        the widthScalar of the image
     * @param heightScalar       the heightScalar of the image
     * @param referenceComponent referenceComponent used to scale the image
     */
    public ScaledImageComponent(String name, double widthScalar, double heightScalar, Component referenceComponent) {
        this(name, widthScalar, heightScalar, referenceComponent, true);
    }

    public ScaledImageComponent(String name, Supplier<Double> sizeScalar, Component referenceComponent, boolean keepRatio) {
        this(name, sizeScalar, sizeScalar, referenceComponent, keepRatio);
        this.squared = true;
    }

    /**
     * ScaledImageConstructor scaled by size relative to referenceComponent (keep ratio component)
     *
     * @param name               name of the image
     * @param sizeScalar         the sizeScalar of the image
     * @param referenceComponent referenceComponent used to scale the image
     * @param keepRatio          keep the ratio of the image or not
     */
    public ScaledImageComponent(String name, double sizeScalar, Component referenceComponent, boolean keepRatio) {
        this(name, sizeScalar, sizeScalar, referenceComponent, keepRatio);
        this.squared = true;
    }

    public ScaledImageComponent(String name, Supplier<Double> sizeScalar, Component referenceComponent) {
        this(name, sizeScalar, referenceComponent, true);
    }

    /**
     * ScaledImageConstructor scaled by size relative to referenceComponent (keep ratio component)
     *
     * @param name               name of the image
     * @param sizeScalar         the sizeScalar of the image
     * @param referenceComponent referenceComponent used to scale the image
     */
    public ScaledImageComponent(String name, double sizeScalar, Component referenceComponent) {
        this(name, sizeScalar, referenceComponent, true);
    }

    /**
     * ScaledImageConstructor having the same size as a referenceComponent
     *
     * @param name               name of the image
     * @param referenceComponent referenceComponent used to scale the image
     * @param keepRatio          keep the ratio of the image or not
     */
    public ScaledImageComponent(String name, Component referenceComponent, boolean keepRatio) {
        super(name);

        this.referenceComponent = referenceComponent;
        this.widthScalar = () -> 1d;
        this.heightScalar = () -> 1d;
        this.keepRatio = keepRatio;
    }

    /**
     * ScaledImageConstructor having the same size as a referenceComponent
     *
     * @param name               name of the image
     * @param referenceComponent referenceComponent used to scale the image
     */
    public ScaledImageComponent(String name, Component referenceComponent) {
        this(name, referenceComponent, true);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        int imageWidth;
        int imageHeight;
        double ratio = 1;
        if (this.keepRatio) {
            ratio = ((double) this.image.getHeight(this)) / ((double) this.image.getWidth(this));
        }

        if (this.keepRatio) {
            imageWidth = Math.min((int) (this.widthScalar.get() * this.referenceComponent.getWidth()), (int) (this.heightScalar.get() * this.referenceComponent.getHeight()));
            imageHeight = Math.min((int) (this.widthScalar.get() * this.referenceComponent.getWidth() * ratio), (int) (this.heightScalar.get() * this.referenceComponent.getHeight()));
        } else {
            imageWidth = (int) (this.widthScalar.get() * this.referenceComponent.getWidth());
            imageHeight = (int) (this.heightScalar.get() * this.referenceComponent.getHeight());

            if (this.squared) {
                int mini = Math.min(imageWidth, imageHeight);
                imageWidth = mini;
                imageHeight = mini;
            }
        }

        this.setCustomSize(imageWidth, imageHeight);

        super.paintComponent(graphics);
    }
}
