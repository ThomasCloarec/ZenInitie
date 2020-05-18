package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.subviews.gameview.GameView;
import view.subviews.menuview.MenuView;

/**
 * The interface View.
 *
 * @param <MenuControllerT> the type parameter
 * @param <GameControllerT> the type parameter
 */
public interface View<MenuControllerT extends MenuController, GameControllerT extends GameController> {
    /**
     * Create game view game view.
     *
     * @param gameController the game controller
     * @return the game view
     */
    GameView createGameView(GameControllerT gameController);

    /**
     * Create menu view menu view.
     *
     * @param menuController the menu controller
     * @return the menu view
     */
    MenuView createMenuView(MenuControllerT menuController);

    /**
     * Close.
     */
    void close();
}
