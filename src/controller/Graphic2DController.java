package controller;

import controller.game.Graphic2DGameController;
import controller.listeners.ImageOnClickListener;
import controller.menu.Graphic2DMenuController;
import model.game.Game;
import model.menu.Menu;
import view.Graphical2DView;
import view.View;
import view.utils.components.ImageComponent;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class Graphic2DController extends Controller {
    private static final int MAX_COUNT = 4;
    private static int count;
    /**
     * The view of the application (can either be textual or graphical).
     */
    private final View<Graphic2DMenuController, Graphic2DGameController> view;

    private Graphic2DController() {
        this.view = new Graphical2DView();
        this.newMenu();
    }

    public static Graphic2DController createInstance() {
        Graphic2DController graphic2DController = null;

        if (Graphic2DController.count < Graphic2DController.MAX_COUNT) {
            graphic2DController = new Graphic2DController();
            Graphic2DController.count++;
        }
        return graphic2DController;
    }

    public static KeyAdapter getViewKeyListener(Graphical2DView graphical2DView) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE && graphical2DView.isFullscreenModeActivated()) {
                    graphical2DView.toggleFullScreen();
                }
            }
        };
    }

    public static WindowAdapter getExitClickListener(View<Graphic2DMenuController, Graphic2DGameController> graphical2DView) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                graphical2DView.close();
            }
        };
    }

    @Override
    protected void newGame(Menu menu) {
        Game game = new Game(menu.isAiMode(), menu.isDuoMode(), menu.isOnlineMode());
        Graphic2DGameController gameController = new Graphic2DGameController(game, this::newMenu);
        game.addObserver(this.view.createGameView(gameController));
    }

    @Override
    protected void newMenu() {
        Menu menu = new Menu();
        Graphic2DMenuController menuController = new Graphic2DMenuController(menu, this::newGame);
        menu.addObserver(this.view.createMenuView(menuController));
    }

    public static MouseAdapter getImageOnClickListener(ImageComponent imageComponent, Runnable runnable) {
        return new ImageOnClickListener(runnable, imageComponent);
    }

    public static ComponentAdapter getResizeListener(Component component) {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                component.doLayout();
                component.revalidate();
                component.repaint();
            }
        };
    }
}
