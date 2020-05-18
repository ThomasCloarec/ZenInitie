package view.utils;

import java.awt.Color;

/**
 * The type Extended color.
 */
public class ExtendedColor extends Color {
    /**
     * The constant CUSTOM_BLUE.
     */
    public static final ExtendedColor CUSTOM_BLUE = new ExtendedColor(23, 23, 220);
    /**
     * The constant CUSTOM_GREEN.
     */
    public static final ExtendedColor CUSTOM_GREEN = new ExtendedColor(36, 214, 0);
    /**
     * The constant CUSTOM_GREY.
     */
    public static final ExtendedColor CUSTOM_GREY = new ExtendedColor(46, 46, 46);
    /**
     * The constant CUSTOM_LIGHT_GREY.
     */
    public static final ExtendedColor CUSTOM_LIGHT_GREY = new ExtendedColor(96, 96, 96);
    /**
     * The constant CUSTOM_RED.
     */
    public static final ExtendedColor CUSTOM_RED = new ExtendedColor(250, 23, 23);
    /**
     * The constant TRANSPARENT.
     */
    public static final ExtendedColor TRANSPARENT = new ExtendedColor(0, 0, 0, 0);

    /**
     * Instantiates a new Extended color.
     *
     * @param red   the red
     * @param green the green
     * @param blue  the blue
     */
    public ExtendedColor(int red, int green, int blue) {
        super(red, green, blue);
    }

    /**
     * Instantiates a new Extended color.
     *
     * @param red   the red
     * @param green the green
     * @param blue  the blue
     * @param alpha the alpha
     */
    public ExtendedColor(int red, int green, int blue, int alpha) {
        super(red, green, blue, alpha);
    }

    /**
     * Brighter extended color.
     *
     * @param delta the delta
     * @return the extended color
     */
    public ExtendedColor brighter(int delta) {
        return new ExtendedColor(this.getRed() + delta, this.getGreen() + delta, this.getBlue() + delta);
    }

    /**
     * Darker extended color.
     *
     * @param delta the delta
     * @return the extended color
     */
    public ExtendedColor darker(int delta) {
        return new ExtendedColor(this.getRed() - delta, this.getGreen() - delta, this.getBlue() - delta);
    }
}
