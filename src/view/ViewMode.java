package view;

public enum ViewMode {
    TEXTUAL,
    GRAPHICAL_2D,
    GRAPHICAL_3D;

    public static boolean contains(String test) {
        for (ViewMode c : ViewMode.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
