package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.subviews.gameview.GameView;
import view.subviews.menuview.MenuView;

public interface View<MenuControllerT extends MenuController, GameControllerT extends GameController> {
    GameView createGameView(GameControllerT gameController);

    MenuView createMenuView(MenuControllerT menuController);

    void close();
}
