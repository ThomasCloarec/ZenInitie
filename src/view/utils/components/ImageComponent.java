package view.utils.components;

import com.bulenkov.iconloader.util.Scalr;
import controller.Graphic2DController;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
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
    protected Image baseImage;
    /**
     * The condition that returns if the image has to be visible or not
     */
    protected BooleanSupplier visibleCondition = () -> true;
    /**
     * The resized image of the component to draw
     */
    private BufferedImage resizedImage;
    /**
     * Useful for gif, the image loses a bit of quality but it is not buffered so image animation is kept.
     */
    protected boolean noBufferingResize;

    /**
     * Allow an imageComponent to be copied in another while still saving resources on not having to resize multiple images, resize once => show everywhere.
     *
     * @param imageComponent the reference imageComponent
     */
    /*public ImageComponent(ImageComponent imageComponent) {
        this.setCustomSize(imageComponent.getC);
        this.visibleCondition = imageComponent.visibleCondition;<
        this.resizedImage = imageComponent.resizedImage;
        this.setOpaque(false);
    }*/

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
            double ratio = ((double) this.baseImage.getHeight(this)) / this.baseImage.getWidth(this);
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
        this.setBaseImage(name);
        this.setOpaque(false);
        this.noBufferingResize = originalSize;

        if (originalSize) {
            this.setCustomSize(this.baseImage.getWidth(this), this.baseImage.getHeight(this));
        }

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                ImageComponent.this.resizedImage = Scalr.resize(ImageComponent.toBufferedImage(ImageComponent.this.baseImage), Scalr.Method.AUTOMATIC, ImageComponent.this.getWidth(), ImageComponent.this.getHeight());
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
        ImageIcon imageIcon = new ImageIcon(ImageComponent.class.getResource(ImageComponent.pathPrefix + name));
        ImageComponent.images.put(name, imageIcon.getImage());
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        BufferedImage bimage;

        if (img instanceof BufferedImage) {
            bimage = (BufferedImage) img;
        } else {
            bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
        }

        return bimage;
    }

    public void addOnClick(Runnable onClick) {
        this.addMouseListener(Graphic2DController.getImageOnClickListener(this, onClick));
    }

    protected void setCustomSize(int width, int height) {
        this.setCustomSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.visibleCondition.getAsBoolean()) {
            if (this.noBufferingResize) {
                graphics.drawImage(this.baseImage, 0, 0, this.getWidth(), this.getHeight(), this);
            } else {
                graphics.drawImage(this.resizedImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    public void setBaseImage(String name) {
        if (!ImageComponent.images.containsKey(name)) {
            ImageComponent.loadImage(name);
        }
        this.baseImage = ImageComponent.images.get(name);

        if (!this.noBufferingResize) {
            this.resizedImage = ImageComponent.toBufferedImage(this.baseImage);
        }
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
