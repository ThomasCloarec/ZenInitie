package view;

import com.bulenkov.darcula.DarculaLaf;
import controller.game.GameController;
import controller.menu.MenuController;
import view.subviews.gameview.GameView;
import view.subviews.gameview.Graphical2DGameView;
import view.subviews.menuview.Graphical2DMenuView;
import view.subviews.menuview.MenuView;
import view.utils.ExtendedColor;
import view.utils.components.ImageComponent;
import view.utils.components.PopUpComponent;
import view.utils.text.AppText;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Graphical2DView extends JFrame implements View {
    private static final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private boolean fullscreenModeActivated;

    public Graphical2DView() {
        SwingUtilities.invokeLater(() -> {
            ImageComponent.loadImage("logo_zen.png");
            ImageComponent.loadImage("blue_dragon.png");
            ImageComponent.loadImage("red_dragon.png");
            ImageComponent.loadImage("icons/exit.png");
            ImageComponent.loadImage("left_baner.png");
            ImageComponent.loadImage("right_baner.png");

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

            this.setTitle(AppText.getTextFor("global.frame.title"));
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
        this.dispose();

        if (this.fullscreenModeActivated) {
            this.goFullScreen();
            PopUpComponent popUpComponent = new PopUpComponent("<html><h1>" + AppText.getTextFor("global.fullscreen.activated.message") + "</h1></html>");
            popUpComponent.display();
        } else {
            this.setSize(1200, 600);
            this.setLocationRelativeTo(null);
            this.goWindowedScreen();
        }
        this.setVisible(true);
    }

    private void goFullScreen() {
        this.dispose();
        this.setUndecorated(true);
        Graphical2DView.graphicsDevice.setFullScreenWindow(this);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void goWindowedScreen() {
        this.dispose();
        Graphical2DView.graphicsDevice.setFullScreenWindow(null);
        this.setUndecorated(false);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    @Override
    public GameView createGameView(GameController gameController) {
        Graphical2DGameView graphical2DGameView = new Graphical2DGameView(gameController);
        if (this.fullscreenModeActivated) {
            this.goFullScreen();
        } else {
            this.goWindowedScreen();
        }
        this.setContentPane(graphical2DGameView);

        return graphical2DGameView;
    }

    @Override
    public MenuView createMenuView(MenuController menuController) {
        Graphical2DMenuView graphical2DMenuView = new Graphical2DMenuView(menuController);
        if (this.fullscreenModeActivated) {
            this.goFullScreen();
        } else {
            this.goWindowedScreen();
        }
        this.setContentPane(graphical2DMenuView);

        return graphical2DMenuView;
    }

    public boolean isFullscreenModeActivated() {
        return this.fullscreenModeActivated;
    }
}
