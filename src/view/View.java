package view;

import view.sub_view.GameView;
import view.sub_view.MenuView;

public interface View {
    void setGameView(GameView gameView);

    void setMenuView(MenuView menuView);
}
