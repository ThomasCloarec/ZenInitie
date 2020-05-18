package view.utils.text;

import java.util.Locale;

/**
 * The enum Language.
 */
public enum Language {
    /**
     * English language.
     */
    ENGLISH(Locale.ENGLISH),
    /**
     * French language.
     */
    FRENCH(Locale.FRANCE);
    /**
     * The Locale.
     */
    private final Locale locale;

    /**
     * Instantiates a new Language.
     *
     * @param locale the locale
     */
    Language(Locale locale) {
        this.locale = locale;
    }

    /**
     * Gets locale.
     *
     * @return the locale
     */
    public Locale getLocale() {
        return this.locale;
    }
}
