package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.subviews.gameview.GameView;
import view.subviews.menuview.MenuView;

public interface View {
    GameView createGameView(GameController gameController);

    MenuView createMenuView(MenuController menuController);
}
