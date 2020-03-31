package view;

import view.sub_views.GameView;
import view.sub_views.MenuView;
import view.sub_views.graphical_2d.Graphical2DGameView;
import view.sub_views.graphical_2d.Graphical2DMenuView;
import view.utils.GameText;

import javax.swing.*;

public class GraphicalView extends JFrame implements View {
    public GraphicalView() {
        SwingUtilities.invokeLater(() -> {
            this.setTitle(GameText.get("frame_title"));
            this.setSize(1080, 720);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        });
    }

    @Override
    public void setGameView(GameView gameView) {
        SwingUtilities.invokeLater(() -> this.setContentPane((Graphical2DGameView) gameView));
    }

    @Override
    public void setMenuView(MenuView menuView) {
        SwingUtilities.invokeLater(() -> this.setContentPane((Graphical2DMenuView) menuView));
    }
}
