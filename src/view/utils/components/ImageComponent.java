package view.utils.components;

import com.bulenkov.iconloader.util.Scalr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
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
     * The loaded image of the component
     */
    protected Image image;
    /**
     * The condition that returns if the image has to be visible or not
     */
    protected BooleanSupplier visibleCondition = () -> true;
    /**
     * The resized image of the component to draw
     */
    private BufferedImage bufferedImage;

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

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                ImageComponent.this.bufferedImage = Scalr.resize(ImageComponent.toBufferedImage(ImageComponent.this.image), Scalr.Method.AUTOMATIC, ImageComponent.this.getWidth(), ImageComponent.this.getHeight());
                ImageComponent.this.repaint();
            }
        });
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

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
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
            graphics.drawImage(this.bufferedImage, 0, 0, this.getWidth(), this.getHeight(), this);
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
