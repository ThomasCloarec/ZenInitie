package view;

import com.bulenkov.darcula.DarculaLaf;
import controller.game.GameController;
import controller.menu.MenuController;
import view.sub_views.game_view.GameView;
import view.sub_views.game_view.Graphical2DGameView;
import view.sub_views.menu_view.Graphical2DMenuView;
import view.sub_views.menu_view.MenuView;
import view.utils.ExtendedColor;
import view.utils.Sound;
import view.utils.components.PopUpComponent;
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

            new Sound("hugo.mp3");

            this.setTitle(getTextFor("global.frame.title"));
            this.setSize(1200, 600);
            this.setMinimumSize(new Dimension(700, 350));
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setBackground(ExtendedColor.CUSTOM_GREY);
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
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (this.fullscreenModeActivated) {
            if (!graphicsDevice.isFullScreenSupported()) {
                throw new UnsupportedOperationException("Fullscreen mode is unsupported.");
            }
            graphicsDevice.setFullScreenWindow(this);
            new PopUpComponent("<html><h1>" + getTextFor("global.fullscreen.activated.message") + "</h1></html>");
        } else {
            graphicsDevice.setFullScreenWindow(null);
            this.setSize(1200, 600);
            this.setLocationRelativeTo(null);
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
