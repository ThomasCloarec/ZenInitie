package view.utils;

import java.awt.*;

public class AppColor extends Color {
    public static final Color CUSTOM_BLUE = new Color(23, 23, 220);
    public static final Color CUSTOM_GREY = new Color(46, 46, 46);
    public static final Color CUSTOM_LIGHT_GREY = new Color(96, 96, 96);
    public static final Color CUSTOM_RED = new Color(250, 23, 23);

    public AppColor(int red, int green, int blue) {
        super(red, green, blue);
    }

    public AppColor(int red, int green, int blue, int alpha) {
        super(red, green, blue, alpha);
    }
}
