package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.sub_views.game_view.GameView;
import view.sub_views.menu_view.MenuView;

public interface View {
    GameView createGameView(GameController gameController);

    MenuView createMenuView(MenuController menuController);
}
