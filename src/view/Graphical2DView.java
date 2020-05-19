package view;

import com.bulenkov.darcula.DarculaLaf;
import controller.Graphic2DController;
import controller.game.Graphic2DGameController;
import controller.menu.Graphic2DMenuController;
import view.subviews.gameview.GameView;
import view.subviews.gameview.Graphical2DGameView;
import view.subviews.menuview.Graphical2DMenuView;
import view.subviews.menuview.MenuView;
import view.utils.ExtendedColor;
import view.utils.Sound;
import view.utils.components.FireComponent;
import view.utils.components.ImageComponent;
import view.utils.text.AppText;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The type Graphical 2 d view.
 */
public class Graphical2DView extends JFrame implements View<Graphic2DMenuController, Graphic2DGameController> {
    /**
     * The constant graphicsDevice.
     */
    private static final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    /**
     * The Sounds.
     */
    private final Collection<Sound> sounds = new ArrayList<>();
    /**
     * The Fullscreen mode activated.
     */
    private boolean fullscreenModeActivated;

    /**
     * Instantiates a new Graphical 2 d view.
     *
     * @param graphic2DController the graphic 2 d controller
     */
    public Graphical2DView(Graphic2DController graphic2DController) {
        SwingUtilities.invokeLater(() -> {
            // preload images
            ImageComponent.loadImage("logo_zen.png");
            ImageComponent.loadImage("blue_dragon.png");
            ImageComponent.loadImage("red_dragon.png");
            ImageComponent.loadImage("icons/exit.png");
            ImageComponent.loadImage("icons/volume_none.png");
            ImageComponent.loadImage("icons/volume_low.png");
            ImageComponent.loadImage("icons/volume_medium.png");
            ImageComponent.loadImage("icons/volume_high.png");
            ImageComponent.loadImage("left_baner.png");
            ImageComponent.loadImage("right_baner.png");
            ImageComponent.loadImage("crown.png");
            ImageComponent.loadImage("finish.png");

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
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            this.setBackground(ExtendedColor.CUSTOM_GREY);
            this.setFocusable(true);

            this.addKeyListener(Graphic2DController.getViewKeyListener(this));
            this.addWindowListener(graphic2DController.getExitClickListener(this));
            this.addComponentListener(Graphic2DController.getResizeListener(this));
        });
    }

    /**
     * Toggle full screen.
     */
    public void toggleFullScreen() {
        this.fullscreenModeActivated = !this.fullscreenModeActivated;
        this.dispose();

        if (this.fullscreenModeActivated) {
            this.goFullScreen();
        } else {
            this.setSize(1200, 600);
            this.setLocationRelativeTo(null);
            this.goWindowedScreen();
        }
        this.setVisible(true);
    }

    /**
     * Go full screen.
     */
    private void goFullScreen() {
        this.dispose();
        this.setUndecorated(true);
        Graphical2DView.graphicsDevice.setFullScreenWindow(this);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * Go windowed screen.
     */
    private void goWindowedScreen() {
        this.dispose();
        Graphical2DView.graphicsDevice.setFullScreenWindow(null);
        this.setUndecorated(false);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    /**
     * Create game view game view.
     *
     * @param gameController the game controller
     * @return the game view
     */
    @Override
    public GameView createGameView(Graphic2DGameController gameController) {
        Graphical2DGameView graphical2DGameView = new Graphical2DGameView(gameController);

        new Thread(() -> {
            FireComponent fireComponent = new FireComponent();
            this.setContentPane(fireComponent);
            if (this.fullscreenModeActivated) {
                this.goFullScreen();
            } else {
                this.goWindowedScreen();
            }

            fireComponent.launch();

            this.setContentPane(graphical2DGameView);
            if (this.fullscreenModeActivated) {
                this.goFullScreen();
            } else {
                this.goWindowedScreen();
            }
        }).start();

        return graphical2DGameView;
    }

    /**
     * Create menu view menu view.
     *
     * @param menuController the menu controller
     * @return the menu view
     */
    @Override
    public MenuView createMenuView(Graphic2DMenuController menuController) {
        this.sounds.forEach(Sound::stop);
        Sound sound = new Sound("lotus_du_printemps_tombant.mp3");
        sound.play();
        sound.loop();
        this.sounds.add(sound);

        Graphical2DMenuView graphical2DMenuView = new Graphical2DMenuView(menuController);
        this.setContentPane(graphical2DMenuView);
        if (this.fullscreenModeActivated) {
            this.goFullScreen();
        } else {
            this.goWindowedScreen();
        }

        return graphical2DMenuView;
    }

    /**
     * Close.
     */
    @Override
    public void close() {
        this.dispose();
        this.sounds.forEach(Sound::stop);
    }

    /**
     * Is fullscreen mode activated boolean.
     *
     * @return the boolean
     */
    public boolean isFullscreenModeActivated() {
        return this.fullscreenModeActivated;
    }
}
