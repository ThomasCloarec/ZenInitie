package controller;

import controller.game.GameController;
import controller.menu.Graphic2DMenuController;
import controller.menu.MenuController;
import model.game.Game;
import model.menu.Menu;
import view.TextualView;
import view.View;

public final class TextualController extends Controller {
    private static final int MAX_COUNT = 1;
    private static int count;
    /**
     * The view of the application (can either be textual or graphical).
     */
    private final View<MenuController, GameController> view;

    private TextualController() {
        this.view = new TextualView();
        this.newMenu();
    }

    public static TextualController createInstance() {
        TextualController textualController = null;

        if (TextualController.count < TextualController.MAX_COUNT) {
            textualController = new TextualController();
            TextualController.count++;
        }

        return textualController;
    }

    @Override
    protected void newGame(Menu menu) {
        Game game = new Game(menu.isAiMode(), menu.isDuoMode(), menu.isOnlineMode());
        GameController gameController = new GameController(game, this::newMenu);
        game.addObserver(this.view.createGameView(gameController));
    }

    @Override
    protected void newMenu() {
        Menu menu = new Menu();
        Graphic2DMenuController menuController = new Graphic2DMenuController(menu, this::newGame);
        menu.addObserver(this.view.createMenuView(menuController));
    }
}
