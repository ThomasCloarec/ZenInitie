package view.utils.components;

import javax.swing.*;
import java.awt.*;
import java.util.function.BooleanSupplier;

/**
 * A generic component to show images
 */
public class ImageComponent extends JPanel {
    /**
     * The prefix of the path for the image. It directly goes into the images directory
     */
    private static final String pathPrefix = "/view/resources/images/";
    /**
     * The image of the component
     */
    protected final Image image;
    /**
     * The condition that returns if the image has to be visible or not
     */
    protected BooleanSupplier visibleCondition = () -> true;

    /**
     * ImageComponent constructor with custom width and height
     *
     * @param name   name of the image
     * @param width  width of the image
     * @param height height of the image
     */
    public ImageComponent(String name, int width, int height) {
        this(name);
        this.setCustomSize(width, height);
    }

    /**
     * ImageComponent constructor with custom size (keep ratio component)
     *
     * @param name      name of the image
     * @param size      size of the image
     * @param keepRatio keep the ratio of the image or not
     */
    public ImageComponent(String name, int size, boolean keepRatio) {
        this(name);
        if (keepRatio) {
            double ratio = ((double) this.image.getHeight(this)) / ((double) this.image.getWidth(this));
            this.setCustomSize(size, (int) (size * ratio));
        } else {
            this.setCustomSize(size, size);
        }
    }

    /**
     * ImageComponent constructor with custom size (squared component)
     *
     * @param name name of the image
     * @param size size of the image
     */
    public ImageComponent(String name, int size) {
        this(name, size, true);
    }

    /**
     * ImageComponent constructor with original size
     *
     * @param name name of the image
     */
    public ImageComponent(String name, boolean originalSize) {
        Image image;
        image = new ImageIcon(ImageComponent.class.getResource(ImageComponent.pathPrefix + name)).getImage();

        if (originalSize) {
            this.setCustomSize(image.getWidth(this), image.getHeight(this));
        }
        this.image = image;

        this.setOpaque(false);
    }

    protected ImageComponent(String name) {
        this(name, false);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.visibleCondition.getAsBoolean()) {
            graphics.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    protected void setCustomSize(int width, int height) {
        this.setCustomSize(new Dimension(width, height));
    }

    protected void setCustomSize(Dimension dimension) {
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setSize(dimension);
    }

    public void setVisibleCondition(BooleanSupplier visibleCondition) {
        this.visibleCondition = visibleCondition;
    }
}
