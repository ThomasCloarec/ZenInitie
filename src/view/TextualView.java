package view;

import controller.game.GameController;
import controller.menu.MenuController;
import view.subviews.gameview.GameView;
import view.subviews.gameview.TextualGameView;
import view.subviews.menuview.MenuView;
import view.subviews.menuview.TextualMenuView;
import view.utils.Sound;
import view.utils.text.AppText;

/**
 * The type Textual view.
 */
public class TextualView implements View<MenuController, GameController> {
    /**
     * The Music.
     */
    private Sound music;

    /**
     * Instantiates a new Textual view.
     */
    public TextualView() {
        System.out.println("███████╗███████╗███╗   ██╗    ██╗     ▄█╗██╗███╗   ██╗██╗████████╗██╗███████╗\n" +
                "╚══███╔╝██╔════╝████╗  ██║    ██║     ╚═╝██║████╗  ██║██║╚══██╔══╝██║██╔════╝\n" +
                "  ███╔╝ █████╗  ██╔██╗ ██║    ██║        ██║██╔██╗ ██║██║   ██║   ██║█████╗  \n" +
                " ███╔╝  ██╔══╝  ██║╚██╗██║    ██║        ██║██║╚██╗██║██║   ██║   ██║██╔══╝  \n" +
                "███████╗███████╗██║ ╚████║    ███████╗   ██║██║ ╚████║██║   ██║   ██║███████╗\n" +
                "╚══════╝╚══════╝╚═╝  ╚═══╝    ╚══════╝   ╚═╝╚═╝  ╚═══╝╚═╝   ╚═╝   ╚═╝╚══════╝\n");

        System.out.println("████████████████████████████████████████\n" +
                "██      by Thomas Cloarec - 2020      ██\n" +
                "██  First Year Undergraduate Project  ██\n" +
                "████████████████████████████████████████\n");
        System.out.println(AppText.preInformation + AppText.getTextFor("global.welcome"));
    }

    /**
     * Create game view game view.
     *
     * @param gameController the game controller
     * @return the game view
     */
    @Override
    public GameView createGameView(GameController gameController) {
        Sound sound = new Sound("tami-levant-du-printemps.mp3");
        if (this.music != null) {
            this.music.fadeTransitionTo(sound);
        } else {
            sound.play();
            sound.loop();
        }
        this.music = sound;

        return new TextualGameView(gameController);
    }

    /**
     * Create menu view menu view.
     *
     * @param menuController the menu controller
     * @return the menu view
     */
    @Override
    public MenuView createMenuView(MenuController menuController) {
        Sound sound = new Sound("lotus-du-printemps-tombant.mp3");
        if (this.music != null) {
            this.music.fadeTransitionTo(sound);
        } else {
            sound.play();
            sound.loop();
        }
        this.music = sound;

        return new TextualMenuView(menuController);
    }

    /**
     * Close.
     */
    @Override
    public void close() {
        this.music.stop();
    }
}
