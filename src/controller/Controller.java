package controller;

import model.menu.Menu;
import view.ViewMode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The type Controller.
 */
public abstract class Controller {
    /**
     * Instantiates a new Controller.
     */
    protected Controller() {

    }

    /**
     * Create instance controller.
     *
     * @param viewMode the view mode
     * @return the controller
     */
    public static Controller createInstance(ViewMode viewMode) {
        Controller controller = null;

        if (viewMode == ViewMode.TEXTUAL) {
            controller = TextualController.createInstance();
        } else if (viewMode == ViewMode.GRAPHICAL_2D) {
            controller = Graphic2DController.createInstance();
        }
        return controller;
    }

    /**
     * Create instances collection.
     *
     * @param viewMode the view mode
     * @param number   the number
     * @return the collection
     */
    public static Collection<Controller> createInstances(ViewMode viewMode, int number) {
        Collection<Controller> controllers = new ArrayList<>(number);

        Controller controller;
        for (int i = 0; i < number; i++) {
            controller = Controller.createInstance(viewMode);
            if (controller != null) {
                controllers.add(controller);
            }
        }

        return controllers;
    }

    /**
     * Create a new game and launch it using the previously collected information from the menu.
     * This method set up the MVC architectural pattern and the Observer behavioral pattern used during the game.
     *
     * @param menu The menu containing the necessary information to launch the game.
     */
    protected abstract void newGame(Menu menu);

    /**
     * Create a new menu and collect information for the future game.
     * This method set up the MVC architectural pattern and the Observer behavioral pattern used for the menu.
     */
    protected abstract void newMenu();
}
