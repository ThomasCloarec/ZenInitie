package view;

/**
 * The enum View mode.
 */
public enum ViewMode {
    /**
     * Textual view mode.
     */
    TEXTUAL,
    /**
     * Graphical 2 d view mode.
     */
    GRAPHICAL_2D,
    /**
     * Graphical 3 d view mode.
     */
    GRAPHICAL_3D;

    /**
     * Contains boolean.
     *
     * @param test the test
     * @return the boolean
     */
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
