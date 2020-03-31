import controller.game.GameController;
import controller.menu.MenuController;
import model.game.Game;
import model.menu.Menu;
import view.GraphicalView;
import view.TextualView;
import view.View;
import view.ViewMode;
import view.sub_view.GameView;
import view.sub_view.MenuView;
import view.sub_view.graphical_2d.Graphical2DGameView;
import view.sub_view.graphical_2d.Graphical2DMenuView;
import view.sub_view.textual.TextualGameView;
import view.sub_view.textual.TextualMenuView;

public class Main {
    private static ViewMode VIEW_MODE;
    private static View view;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].toUpperCase().equals(ViewMode.GRAPHICAL_2D.name())) {
            Main.VIEW_MODE = ViewMode.GRAPHICAL_2D;
            Main.view = new GraphicalView();
        } else {
            Main.VIEW_MODE = ViewMode.TEXTUAL;
            Main.view = new TextualView();
        }

        Main.showMenu();
    }

    private static void newGame() {
        Game game = new Game();
        GameController gameController = new GameController(game);
        GameView gameView;
        if (Main.VIEW_MODE == ViewMode.GRAPHICAL_2D) {
            gameView = new Graphical2DGameView(gameController);
        } else {
            gameView = new TextualGameView(gameController);
        }
        game.addObserver(gameView);
        Main.view.setGameView(gameView);
    }

    private static void showMenu() {
        Menu menu = new Menu();
        MenuController menuController = new MenuController(menu);
        MenuView menuView;
        if (Main.VIEW_MODE == ViewMode.GRAPHICAL_2D) {
            menuView = new Graphical2DMenuView(menuController);
        } else {
            menuView = new TextualMenuView(menuController);
        }
        menu.addObserver(menuView);
        Main.view.setMenuView(menuView);
    }
}
