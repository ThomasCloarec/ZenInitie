package view;

import view.sub_views.GameView;
import view.sub_views.MenuView;

public interface View {
    void setGameView(GameView gameView);

    void setMenuView(MenuView menuView);
}
