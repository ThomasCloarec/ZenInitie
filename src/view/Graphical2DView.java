package view;

import com.bulenkov.darcula.DarculaLaf;
import controller.game.GameController;
import controller.menu.MenuController;
import view.sub_views.game_view.GameView;
import view.sub_views.game_view.Graphical2DGameView;
import view.sub_views.menu_view.Graphical2DMenuView;
import view.sub_views.menu_view.MenuView;
import view.utils.AppColor;
import view.utils.text.AppText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static view.utils.text.AppText.getTextFor;

public class Graphical2DView extends JFrame implements View {
    private boolean fullscreenModeActivated;

    public Graphical2DView() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new DarculaLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            try {
                AppText.setCustomFont(Font.createFont(Font.TRUETYPE_FONT, Graphical2DView.class.getResourceAsStream("/view/resources/fonts/go3v2.ttf")));
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }

            this.setTitle(getTextFor("global.frame.title"));
            this.setSize(1200, 600);
            this.setMinimumSize(new Dimension(700, 350));
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setBackground(AppColor.CUSTOM_GREY);
            this.setFocusable(true);

            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent keyEvent) {
                    super.keyPressed(keyEvent);
                    if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE && Graphical2DView.this.fullscreenModeActivated) {
                        Graphical2DView.this.toggleFullScreen();
                    }
                }
            });
        });
    }

    public void toggleFullScreen() {
        this.fullscreenModeActivated = !this.fullscreenModeActivated;
        this.dispose();

        this.setUndecorated(this.fullscreenModeActivated);

        if (this.fullscreenModeActivated) {
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
            this.setVisible(true);
            this.getContentPane().repaint();
            JLabel label = new JLabel("<html><h2>" + getTextFor("global.fullscreen.activated.message") + "</h2></html>");
            label.setFont(AppText.getCustomFont());
            label.setHorizontalAlignment(JLabel.CENTER);
            JOptionPane.showMessageDialog(null, label, getTextFor("global.fullscreen.activated.title"), JOptionPane.PLAIN_MESSAGE);
        } else {
            this.setExtendedState(JFrame.NORMAL);
            this.setSize(1200, 600);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setResizable(true);
        }
    }

    @Override
    public GameView createGameView(GameController gameController) {
        Graphical2DGameView graphical2DGameView = new Graphical2DGameView(gameController);
        SwingUtilities.invokeLater(() -> {
            this.setContentPane(graphical2DGameView);
            this.setVisible(true);
        });
        return graphical2DGameView;
    }

    @Override
    public MenuView createMenuView(MenuController menuController) {
        Graphical2DMenuView graphical2DMenuView = new Graphical2DMenuView(menuController);
        SwingUtilities.invokeLater(() -> {
            this.setContentPane(graphical2DMenuView);
            this.setVisible(true);
        });

        return graphical2DMenuView;
    }

    public boolean isFullscreenModeActivated() {
        return this.fullscreenModeActivated;
    }
}
