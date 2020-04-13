package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.sub_views.game_view.GameView;
import view.sub_views.game_view.Graphical2DGameView;
import view.sub_views.menu_view.Graphical2DMenuView;
import view.sub_views.menu_view.MenuView;
import view.utils.AppText;

import javax.swing.*;

public class Graphical2DView extends JFrame implements View {
    public Graphical2DView() {
        SwingUtilities.invokeLater(() -> {
            this.setTitle(AppText.getTextFor("global.frame.title"));
            this.setSize(1080, 720);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        });
    }

    @Override
    public GameView createGameView(GameController gameController) {
        Graphical2DGameView graphical2DGameView = new Graphical2DGameView(gameController);
        SwingUtilities.invokeLater(() -> this.setContentPane(graphical2DGameView));
        return graphical2DGameView;
    }

    @Override
    public MenuView createMenuView(MenuController menuController) {
        Graphical2DMenuView graphical2DMenuView = new Graphical2DMenuView(menuController);
        SwingUtilities.invokeLater(() -> this.setContentPane(graphical2DMenuView));
        return graphical2DMenuView;
    }
}
