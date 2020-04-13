package view.utils.components;

import javax.swing.*;
import java.awt.*;

/**
 * A generic component to show images (in a label or a button)
 */
public class ImageComponent {
    /**
     * The prefix of the path for the image. It directly goes into the images directory
     */
    private static final String pathPrefix = "../../resources/images/";
    /**
     * The image of the component
     */
    private final ImageIcon miniature;
    /**
     * The size of the component
     */
    private int size;

    /**
     * ImageComponent constructor with custom width and height
     *
     * @param name   name of the image
     * @param width  width of the image
     * @param height height of the image
     */
    public ImageComponent(String name, int width, int height) {
        name = ImageComponent.pathPrefix + name;
        ImageIcon icon = new ImageIcon(ImageComponent.class.getResource(name));
        Image image = icon.getImage();
        this.miniature = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        this.size = Math.max(width, height);
    }

    /**
     * ImageComponent constructor in default size
     *
     * @param name name of the image
     */
    public ImageComponent(String name) {
        name = ImageComponent.pathPrefix + name;
        this.miniature = new ImageIcon(ImageComponent.class.getResource(name));
    }

    /**
     * ImageComponent constructor with custom size (squared component)
     *
     * @param name name of the image
     * @param size size of the image
     */
    public ImageComponent(String name, int size) {
        this(name, size, size);
    }

    /**
     * Get the imageComponent as a button
     *
     * @return the button containing the image
     */
    public JButton getAsButton() {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setIcon(this.miniature);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setSize(this.size, this.size);
        button.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        return button;
    }

    /**
     * Get the imageComponent as a label
     *
     * @return the label containing the image
     */
    public JLabel getAsLabel() {
        JLabel label = new JLabel();
        label.setIcon(this.miniature);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        return label;
    }
}