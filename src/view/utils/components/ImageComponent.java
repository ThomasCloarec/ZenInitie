package view.utils.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.function.BooleanSupplier;

/**
 * A generic component to show images
 */
public class ImageComponent extends JPanel {
    /**
     * The prefix of the path for the image. It directly goes into the images directory
     */
    static final String pathPrefix = "/view/resources/images/";
    private static final HashMap<String, Image> images = new HashMap<>();
    /**
     * The image of the component
     */
    protected Image image;
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
     * ImageComponent constructor with original size
     *
     * @param name name of the image
     */
    public ImageComponent(String name, boolean originalSize) {
        if (originalSize) {
            this.setCustomSize(this.image.getWidth(this), this.image.getHeight(this));
        }

        this.setImage(name);
        this.setOpaque(false);
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

    protected ImageComponent(String name) {
        this(name, false);
    }

    public static void loadImage(String name) {
        ImageComponent.images.put(name, new ImageIcon(ImageComponent.class.getResource(ImageComponent.pathPrefix + name)).getImage());
    }

    public void addOnClick(Runnable onClick) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                onClick.run();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                ImageComponent.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    protected void setCustomSize(int width, int height) {
        this.setCustomSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.visibleCondition.getAsBoolean()) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(new ImageIcon(this.image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)).getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    protected void setCustomSize(Dimension dimension) {
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setSize(dimension);
    }

    public void setImage(String name) {
        if (!ImageComponent.images.containsKey(name)) {
            ImageComponent.loadImage(name);
        }
        this.image = ImageComponent.images.get(name);
    }

    public void setVisibleCondition(BooleanSupplier visibleCondition) {
        this.visibleCondition = visibleCondition;
    }
}
