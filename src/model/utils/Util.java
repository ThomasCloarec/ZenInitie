package model.utils;

public final class Util {
    /**
     * Prevents instantiation by client code
     */
    private Util() {

    }

    public static boolean getRandomBoolean() {
        return Math.random() * 2 < 1;
    }
}
