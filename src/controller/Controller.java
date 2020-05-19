package controller;

import model.game.Game;
import model.game.network.GameClient;
import model.game.network.GameNetwork;
import model.game.network.GameServer;
import model.menu.Menu;
import model.menu.MenuPage;
import view.ViewMode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The type Controller.
 */
public abstract class Controller {
    /**
     * The Game network launched.
     */
    private boolean gameNetworkLaunched;
    /**
     * The Game network (only used in network mode)
     */
    private GameNetwork gameNetwork;

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
     * Launch the new game.
     * This method set up the MVC architectural pattern and the Observer behavioral pattern used during the game.
     *
     * @param game The launched game.
     */
    protected abstract void newGame(Game game);

    /**
     * Create a new menu and collect information for the future game.
     * This method set up the MVC architectural pattern and the Observer behavioral pattern used for the menu.
     */
    protected void newMenu() {
        if (this.gameNetwork != null) {
            this.gameNetwork.stop();
        }
        this.gameNetworkLaunched = false;
    }

    /**
     * Create a new game and launch it using the previously collected information from the menu.
     * This method set up the MVC architectural pattern and the Observer behavioral pattern used during the game.
     * Launch either online server, online client or offline mode depending on menu choices.
     *
     * @param menu The menu containing the necessary information to launch the game.
     */
    protected void newGame(Menu menu) {
        if (menu.isOnlineClient() || menu.isOnlineServer()) {
            menu.addActualPage(MenuPage.LOBBY);

            if (menu.isOnlineServer()) {
                this.gameNetwork = new GameServer(menu.isAiMode(), menu.isDuoMode(), this::launchGameNetwork, this::newMenu);
            } else if (menu.isOnlineClient()) {
                this.gameNetwork = new GameClient(menu.isAiMode(), menu.isDuoMode(), this::launchGameNetwork, this::newMenu);
            }
        } else {
            this.newGame(new Game(menu.isAiMode(), menu.isDuoMode()));
        }
    }

    /**
     * Stop game server.
     */
    protected void stopGameServer() {
        this.gameNetwork.stop();
    }

    /**
     * Launch game network.
     *
     * @return the boolean
     */
    protected boolean launchGameNetwork() {
        boolean launchedNow = false;
        if (!this.gameNetworkLaunched) {
            this.newGame(this.gameNetwork);
            this.gameNetworkLaunched = true;
            launchedNow = true;
        }
        return launchedNow;
    }
}
