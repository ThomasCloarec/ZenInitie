import controller.Controller;
import controller.Graphic2DController;
import view.ViewMode;
import view.utils.text.AppText;

import java.util.ArrayList;
import java.util.Collection;

/**
 * More information about the game in the README.md file.
 *
 * @author Thomas Cloarec
 */
public class Main {
    /**
     * The list of controllers launched in the app (some may be finished), each controller takes care of a global app instance.
     */
    private static final Collection<Controller> controllers = new ArrayList<>();

    /**
     * This main method launch the application either in textual mode or in graphical mode depending of it's parameters.
     * The possible modes are :
     * <ul>
     * <li>TEXTUAL</li>
     * <li>TEXTUAL-[count]</li>
     * <li>GRAPHICAL_2D</li>
     * <li>GRAPHICAL_2D-[count]</li>
     * </ul>
     *
     * By default the display mode is "GRAPHICAL_2D."
     *
     * @param args The first index of this array may contain the display mode used.
     */
    public static void main(String[] args) {
        boolean badArgumentsJar = false;

        for (String arg : args) {
            String upperArg = arg.toUpperCase();

            if (upperArg.contains("-")) {
                // if the arg is of the form [string]-[string]

                String[] upperArgSplit = upperArg.split("-");

                if (upperArgSplit.length == 2) {
                    // if the arg is of the form [string(not empty)]-[string(not empty)]

                    String testViewMode = upperArgSplit[0];
                    String testCount = upperArgSplit[1];

                    if (ViewMode.contains(testViewMode)) {
                        // if the arg is of the form [viewMode]-[string(not empty)]

                        ViewMode viewMode = ViewMode.valueOf(testViewMode);

                        try {
                            // if there is no exception then the arg is of the form [viewMode]-[count]
                            int count = Integer.parseInt(testCount);

                            // launch "count" number of instances
                            Main.controllers.addAll(Controller.createInstances(viewMode, count));
                        } catch (NumberFormatException e) {
                            badArgumentsJar = true;
                        }
                    }
                } else {
                    badArgumentsJar = true;
                }
            } else if (ViewMode.contains(upperArg)) {
                // If the arg is of the form [viewMode]

                ViewMode viewMode = ViewMode.valueOf(upperArg);
                Main.controllers.add(Controller.createInstance(viewMode));
            }
        }

        if (badArgumentsJar) {
            // If bad arguments passed, then tell it to the user
            System.out.println(AppText.getTextFor("global.badArgumentsJar"));
        }

        // By default, launch a GRAPHIC_2D view
        if (Main.controllers.isEmpty()) {
            Main.controllers.add(Graphic2DController.createInstance());
        }
    }
}