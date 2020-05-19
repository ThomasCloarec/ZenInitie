package controller;

import controller.game.Graphic2DGameController;
import controller.listeners.ImageOnClickListener;
import controller.menu.Graphic2DMenuController;
import model.game.Game;
import model.game.GameData;
import model.menu.Menu;
import view.Graphical2DView;
import view.View;
import view.utils.components.ImageComponent;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The type Graphic 2 d controller.
 */
public final class Graphic2DController extends Controller {
    /**
     * The constant MAX_COUNT.
     */
    private static final int MAX_COUNT = 4;
    /**
     * The constant count.
     */
    private static int count;
    /**
     * The view of the application (can either be textual or graphical).
     */
    private final View<Graphic2DMenuController, Graphic2DGameController> view;

    /**
     * Instantiates a new Graphic 2 d controller.
     */
    private Graphic2DController() {
        this.view = new Graphical2DView(this);
        this.newMenu();
    }

    /**
     * Create instance graphic 2 d controller.
     *
     * @return the graphic 2 d controller
     */
    public static Graphic2DController createInstance() {
        Graphic2DController graphic2DController = null;

        if (Graphic2DController.count < Graphic2DController.MAX_COUNT) {
            graphic2DController = new Graphic2DController();
            Graphic2DController.count++;
        }
        return graphic2DController;
    }

    /**
     * Gets view key listener.
     *
     * @param graphical2DView the graphical 2 d view
     * @return the view key listener
     */
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

    /**
     * Gets exit click listener.
     *
     * @param graphical2DView the graphical 2 d view
     * @return the exit click listener
     */
    public WindowAdapter getExitClickListener(View<Graphic2DMenuController, Graphic2DGameController> graphical2DView) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                graphical2DView.close();
                Graphic2DController.this.stopGameServer();
            }
        };
    }

    /**
     * Gets image on click listener.
     *
     * @param imageComponent the image component
     * @param runnable       the runnable
     * @return the image on click listener
     */
    public static MouseAdapter getImageOnClickListener(ImageComponent imageComponent, Runnable runnable) {
        return new ImageOnClickListener(runnable, imageComponent);
    }

    /**
     * Gets resize listener.
     *
     * @param component the component
     * @return the resize listener
     */
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

    /**
     * New game.
     *
     * @param game the game
     */
    @Override
    protected void newGame(Game game) {
        Graphic2DGameController gameController = new Graphic2DGameController(game, this::newMenu);
        game.addObserver(this.view.createGameView(gameController));
    }

    /**
     * New game.
     *
     * @param gameData the game data
     */
    private void newGame(GameData gameData) {
        Game game = new Game(gameData.aiMode, gameData.duoMode);
        game.setGameData(gameData);
        Graphic2DGameController gameController = new Graphic2DGameController(game, this::newMenu);
        game.addObserver(this.view.createGameView(gameController));
    }

    /**
     * New menu.
     */
    @Override
    protected void newMenu() {
        super.newMenu();
        Menu menu = new Menu();
        Graphic2DMenuController menuController = new Graphic2DMenuController(menu, this::newGame, this::newGame, this::stopGameServer);
        menu.addObserver(this.view.createMenuView(menuController));
    }
}
