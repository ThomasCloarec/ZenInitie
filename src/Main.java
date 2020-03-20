import java.util.Locale;

/**
 * @author Thomas Cloarec
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(GlobalParameters.gameText.getString("intro"));
        GlobalParameters.setLocale(Locale.FRANCE);
        System.out.println(GlobalParameters.gameText.getString("intro"));
    }
}
