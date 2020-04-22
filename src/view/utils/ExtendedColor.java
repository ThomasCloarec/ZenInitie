package view.utils;

import java.awt.*;

public class ExtendedColor extends Color {
    public static final ExtendedColor CUSTOM_BLUE = new ExtendedColor(23, 23, 220);
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
}
