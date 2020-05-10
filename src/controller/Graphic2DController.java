package controller;

import controller.game.Graphic2DGameController;
import controller.menu.Graphic2DMenuController;
import model.game.Game;
import model.menu.Menu;
import view.Graphical2DView;
import view.View;

public final class Graphic2DController extends Controller {
    private static final int MAX_COUNT = 4;
    private static int count;
    /**
     * The view of the application (can either be textual or graphical).
     */
    private final View<Graphic2DMenuController, Graphic2DGameController> view;

    private Graphic2DController() {
        this.view = new Graphical2DView();
        this.newMenu();
    }

    public static Graphic2DController createInstance() {
        Graphic2DController graphic2DController = null;

        if (Graphic2DController.count < Graphic2DController.MAX_COUNT) {
            graphic2DController = new Graphic2DController();
            Graphic2DController.count++;
        }
        return graphic2DController;
    }

    @Override
    protected void newGame(Menu menu) {
        Game game = new Game(menu.isAiMode(), menu.isDuoMode(), menu.isOnlineMode());
        Graphic2DGameController gameController = new Graphic2DGameController(game, this::newMenu);
        game.addObserver(this.view.createGameView(gameController));
    }

    @Override
    protected void newMenu() {
        Menu menu = new Menu();
        Graphic2DMenuController menuController = new Graphic2DMenuController(menu, this::newGame);
        menu.addObserver(this.view.createMenuView(menuController));
    }
}
