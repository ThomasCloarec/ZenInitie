package view.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public final class GameText {
    public static final String preAnswer = "\t • ";
    public static final String preInformation = "\n ━ ";
    public static final String preInput = " ⮕ ";
    public static final String preQuestion = " ➤ ";
    public static ResourceBundle gameText = ResourceBundle.getBundle("view/resources/TextLabels");

    public static String get(String string) {
        return GameText.gameText.getString(string);
    }

    public static void setLocaleLanguage(Locale locale) {
        Locale.setDefault(locale);
        GameText.gameText = ResourceBundle.getBundle("view/resources/TextLabels");
    }
}
