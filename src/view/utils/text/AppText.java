package view.utils.text;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Locale;
import java.util.ResourceBundle;

public final class AppText {
    public static final String preAnswer = "\t · ";
    public static final String preInformation = "\n - ";
    public static final String preInput = " > ";
    public static final String preQuestion = " * ";
    private static Font customFont = new Font("Open sans", Font.PLAIN, 20);
    private static ResourceBundle gameText = ResourceBundle.getBundle("view/resources/textlabels/GameLabels");
    private static ResourceBundle globalText = ResourceBundle.getBundle("view/resources/textlabels/GlobalLabels");
    private static ResourceBundle menuText = ResourceBundle.getBundle("view/resources/textlabels/MenuLabels");
    public static final String preError = "(⌐■_■)︻╦╤─ (" + AppText.getTextFor("global.word.error") + ") → ";

    private AppText() {

    }

    public static boolean hasTextFor(String text) {
        return AppText.getResourceBundleFor(text).containsKey(text);
    }

    public static String getTextFor(String text) {
        return AppText.getResourceBundleFor(text).getString(text);
    }

    public static void setAppLanguage(Language language) {
        Locale.setDefault(language.getLocale());

        AppText.gameText = ResourceBundle.getBundle("view/resources/textlabels/GameLabels");
        AppText.globalText = ResourceBundle.getBundle("view/resources/textlabels/GlobalLabels");
        AppText.menuText = ResourceBundle.getBundle("view/resources/textlabels/MenuLabels");
    }

    public static Font getCustomFont() {
        return AppText.customFont;
    }

    public static void setCustomFont(Font customFont) {
        Font font = customFont.deriveFont(Font.PLAIN, 20.0f);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        AppText.customFont = font;
    }

    private static ResourceBundle getResourceBundleFor(String text) {
        ResourceBundle ret;

        String bundle = text.split("\\.")[0];

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
