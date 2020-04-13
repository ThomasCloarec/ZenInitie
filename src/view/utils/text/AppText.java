package view.utils.text;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public final class AppText {
    public static final String preAnswer = "\t • ";
    public static final String preInformation = "\n ━ ";
    public static final String preInput = " ⮕ ";
    public static final String preQuestion = " ➤ ";
    private static Font customFont = new Font("Open sans", Font.PLAIN, 20);
    private static ResourceBundle gameText = ResourceBundle.getBundle("view/resources/text_labels/GameLabels");
    private static ResourceBundle globalText = ResourceBundle.getBundle("view/resources/text_labels/GlobalLabels");
    private static ResourceBundle menuText = ResourceBundle.getBundle("view/resources/text_labels/MenuLabels");
    public static final String preError = "(⌐■_■)︻╦╤─ (" + AppText.getTextFor("global.word.error") + ") → ";

    private AppText() {

    }

    public static boolean hasTextFor(String string) {
        return AppText.getResourceBundleFor(string).containsKey(string);
    }

    public static String getTextFor(String string) {
        return AppText.getResourceBundleFor(string).getString(string);
    }

    public static void setAppLanguage(Language language) {
        Locale.setDefault(language.getLocale());

        AppText.gameText = ResourceBundle.getBundle("view/resources/text_labels/GameLabels");
        AppText.globalText = ResourceBundle.getBundle("view/resources/text_labels/GlobalLabels");
        AppText.menuText = ResourceBundle.getBundle("view/resources/text_labels/MenuLabels");
    }

    public static Font getCustomFont() {
        return AppText.customFont;
    }

    public static void setCustomFont(Font customFont) {
        customFont = customFont.deriveFont(Font.PLAIN, 20f);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);
        AppText.customFont = customFont;
    }

    private static ResourceBundle getResourceBundleFor(String string) {
        ResourceBundle ret;

        String bundle = string.split("\\.")[0];

        if ("game".equals(bundle)) {
            ret = AppText.gameText;
        } else if ("menu".equals(bundle)) {
            ret = AppText.menuText;
        } else {
            ret = AppText.globalText;
        }

        return ret;
    }
}
