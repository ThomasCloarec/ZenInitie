package view.utils;

import java.awt.Color;

public class ExtendedColor extends Color {
    public static final ExtendedColor CUSTOM_BLUE = new ExtendedColor(23, 23, 220);
    public static final ExtendedColor CUSTOM_GREEN = new ExtendedColor(36, 214, 0);
    public static final ExtendedColor CUSTOM_GREY = new ExtendedColor(46, 46, 46);
    public static final ExtendedColor CUSTOM_LIGHT_GREY = new ExtendedColor(96, 96, 96);
    public static final ExtendedColor CUSTOM_RED = new ExtendedColor(250, 23, 23);
    public static final ExtendedColor TRANSPARENT = new ExtendedColor(0, 0, 0, 0);

    public ExtendedColor(int red, int green, int blue) {
        super(red, green, blue);
    }

    public ExtendedColor(int red, int green, int blue, int alpha) {
        super(red, green, blue, alpha);
    }

    public ExtendedColor brighter(int delta) {
        return new ExtendedColor(this.getRed() + delta, this.getGreen() + delta, this.getBlue() + delta);
    }

    public ExtendedColor darker(int delta) {
        return new ExtendedColor(this.getRed() - delta, this.getGreen() - delta, this.getBlue() - delta);
    }
}
