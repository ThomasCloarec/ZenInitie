package view;

public enum ViewMode {
    TEXTUAL,
    GRAPHICAL_2D,
    GRAPHICAL_3D;

    public static boolean contains(String test) {
        boolean ret = false;

        int i = 0;
        while (i < ViewMode.values().length && !ret) {
            ret = ViewMode.values()[i].name().equals(test);
            i++;
        }

        return ret;
    }
}
