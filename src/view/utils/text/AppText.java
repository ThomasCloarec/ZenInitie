package view.utils.text;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type App text.
 */
public final class AppText {
    /**
     * The constant preAnswer.
     */
    public static final String preAnswer = "\t · ";
    /**
     * The constant preInformation.
     */
    public static final String preInformation = "\n - ";
    /**
     * The constant preInput.
     */
    public static final String preInput = " > ";
    /**
     * The constant preQuestion.
     */
    public static final String preQuestion = " * ";
    /**
     * The constant customFont.
     */
    private static Font customFont = new Font("Open sans", Font.PLAIN, 20);
    /**
     * The constant gameText.
     */
    private static ResourceBundle gameText = ResourceBundle.getBundle("view/resources/textlabels/GameLabels");
    /**
     * The constant globalText.
     */
    private static ResourceBundle globalText = ResourceBundle.getBundle("view/resources/textlabels/GlobalLabels");
    /**
     * The constant menuText.
     */
    private static ResourceBundle menuText = ResourceBundle.getBundle("view/resources/textlabels/MenuLabels");
    /**
     * The constant preError.
     */
    public static final String preError = "(⌐■_■)︻╦╤─ (" + AppText.getTextFor("global.word.error") + ") → ";

    /**
     * Instantiates a new App text.
     */
    private AppText() {

    }

    /**
     * Has text for boolean.
     *
     * @param text the text
     * @return the boolean
     */
    public static boolean hasTextFor(String text) {
        return AppText.getResourceBundleFor(text).containsKey(text);
    }

    /**
     * Gets text for.
     *
     * @param text the text
     * @return the text for
     */
    public static String getTextFor(String text) {
        return AppText.getResourceBundleFor(text).getString(text);
    }

    /**
     * Sets app language.
     *
     * @param language the language
     */
    public static void setAppLanguage(Language language) {
        Locale.setDefault(language.getLocale());

        AppText.gameText = ResourceBundle.getBundle("view/resources/textlabels/GameLabels");
        AppText.globalText = ResourceBundle.getBundle("view/resources/textlabels/GlobalLabels");
        AppText.menuText = ResourceBundle.getBundle("view/resources/textlabels/MenuLabels");
    }

    /**
     * Gets custom font.
     *
     * @return the custom font
     */
    public static Font getCustomFont() {
        return AppText.customFont;
    }

    /**
     * Sets custom font.
     *
     * @param customFont the custom font
     */
    public static void setCustomFont(Font customFont) {
        Font font = customFont.deriveFont(Font.PLAIN, 20.0f);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        AppText.customFont = font;
    }

    /**
     * Gets resource bundle for.
     *
     * @param text the text
     * @return the resource bundle for
     */
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
