package view.utils;

import java.awt.*;

public class AppColor extends Color {
    public static final AppColor CUSTOM_BLUE = new AppColor(23, 23, 220);
    public static final AppColor CUSTOM_GREY = new AppColor(46, 46, 46);
    public static final AppColor CUSTOM_LIGHT_GREY = new AppColor(96, 96, 96);
    public static final AppColor CUSTOM_RED = new AppColor(250, 23, 23);
    public static final AppColor TRANSPARENT = new AppColor(0, 0, 0, 0);

    public AppColor(int red, int green, int blue) {
        super(red, green, blue);
    }

    public AppColor(int red, int green, int blue, int alpha) {
        super(red, green, blue, alpha);
    }
}
