package controller;

import controller.game.GameController;
import controller.menu.Graphic2DMenuController;
import controller.menu.MenuController;
import model.game.Game;
import model.menu.Menu;
import view.TextualView;
import view.View;

/**
 * The type Textual controller.
 */
public final class TextualController extends Controller {
    /**
     * The constant MAX_COUNT.
     */
    private static final int MAX_COUNT = 1;
    /**
     * The constant count.
     */
    private static int count;
    /**
     * The view of the application (can either be textual or graphical).
     */
    private final View<MenuController, GameController> view;

    /**
     * Instantiates a new Textual controller.
     */
    private TextualController() {
        this.view = new TextualView();
        this.newMenu();
    }

    /**
     * Create instance textual controller.
     *
     * @return the textual controller
     */
    public static TextualController createInstance() {
        TextualController textualController = null;

        if (TextualController.count < TextualController.MAX_COUNT) {
            textualController = new TextualController();
            TextualController.count++;
        }

        return textualController;
    }

    /**
     * New game.
     *
     * @param game the menu
     */
    @Override
    protected void newGame(Game game) {
        GameController gameController = new GameController(game, this::newMenu);
        game.addObserver(this.view.createGameView(gameController));
    }

    /**
     * New menu.
     */
    @Override
    protected void newMenu() {
        Menu menu = new Menu();
        Graphic2DMenuController menuController = new Graphic2DMenuController(menu, this::newGame);
        menu.addObserver(this.view.createMenuView(menuController));
    }
}
