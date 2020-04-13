package view.utils.text;

import java.util.Locale;

public enum Language {
    ENGLISH(Locale.ENGLISH),
    FRENCH(Locale.FRANCE);
    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return this.locale;
    }
}
