import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The GlobalParameters class contains information like the text of the game (as it is available in multiple languages) or the game mode.
 */
public class GlobalParameters {
    public static ResourceBundle gameText = ResourceBundle.getBundle("resources/TextLabels");
    public static GameMode gameMode = GameMode.TEXTUAL;

    /**
     * Change the language of the game
     *
     * @param locale the new language
     */
    public static void setLocale(Locale locale) {
        Locale.setDefault(locale);
        gameText = ResourceBundle.getBundle("resources/TextLabels");
    }

    /**
     * Available game modes
     */
    public enum GameMode {
        TEXTUAL,
        GRAPHICAL
    }
}