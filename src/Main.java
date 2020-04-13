import controller.game.GameController;
import controller.menu.MenuController;
import model.game.Game;
import model.game.GameImpl;
import model.menu.Menu;
import view.Graphical2DView;
import view.TextualView;
import view.View;
import view.ViewMode;

/**
 * More information about the game in the README.md file.
 *
 * @author Thomas Cloarec
 */
public class Main {
    /**
     * The view of the application (can either be textual or graphical).
     */
    private static View view;

    /**
     * This main method launch the application either in textual mode or in graphical mode depending of it's parameters.
     *
     * @param args The first index of this array may contain the display mode used.
     *             You can either launch with argument "TEXTUAL" or argument "GRAPHICAL_2D".
     *             By default the display mode is "TEXTUAL."
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].toUpperCase().equals(ViewMode.GRAPHICAL_2D.name())) {
            Main.view = new Graphical2DView();
        } else {
            Main.view = new TextualView();
        }

        Main.newMenu();
    }

    /**
     * Create a new game and launch it using the previously collected information from the menu.
     * This method set up the MVC architectural pattern and the Observer behavioral pattern used during the game.
     *
     * @param menu The menu containing the necessary information to launch the game.
     */
    private static void newGame(Menu menu) {
        Game game = new GameImpl(menu.isAiMode(), menu.isDuoMode(), menu.isOnlineMode());
        GameController gameController = new GameController(game);
        game.addObserver(Main.view.createGameView(gameController));
    }

    /**
     * Create a new game and launch it using the previously collected information from the menu.
     * This method set up the MVC architectural pattern and the Observer behavioral pattern used during the game.
     */
    private static void newMenu() {
        Menu menu = new Menu();
        MenuController menuController = new MenuController(menu, Main::newGame);
        menu.addObserver(Main.view.createMenuView(menuController));
    }
}
