package controller.menu;

import model.menu.Menu;
import view.Graphical2DView;
import view.utils.text.AppText;
import view.utils.text.Language;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.function.Consumer;

/**
 * The type Graphic 2 d menu controller.
 */
public class Graphic2DMenuController extends MenuController {
    /**
     * Instantiates a new Graphic 2 d menu controller.
     *
     * @param menu    the menu
     * @param newGame the new game
     */
    public Graphic2DMenuController(Menu menu, Consumer<? super Menu> newGame) {
        super(menu, newGame);
    }

    /**
     * Gets toggle full screen listener.
     *
     * @param frame the frame
     * @return the toggle full screen listener
     */
    public static ActionListener getToggleFullScreenListener(Graphical2DView frame) {
        return actionEvent -> frame.toggleFullScreen();
    }

    /**
     * Gets button full screen resized.
     *
     * @param frame  the frame
     * @param button the button
     * @return the button full screen resized
     */
    public static ComponentAdapter getButtonFullScreenResized(Graphical2DView frame, JButton button) {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                if (frame.isFullscreenModeActivated()) {
                    button.setText(AppText.getTextFor("global.fullscreen.stop"));
                } else {
                    button.setText(AppText.getTextFor("global.fullscreen.start"));
                }
            }
        };
    }

    /**
     * Gets back previous page listener.
     *
     * @return the back previous page listener
     */
    public ActionListener getBackPreviousPageListener() {
        return actionEvent -> this.backPreviousPage();
    }

    /**
     * Gets change language listener.
     *
     * @return the change language listener
     */
    public ActionListener getChangeLanguageListener() {
        return actionEvent -> this.changeLanguage();
    }

    /**
     * Gets change settings listener.
     *
     * @return the change settings listener
     */
    public ActionListener getChangeSettingsListener() {
        return actionEvent -> this.changeSettings();
    }

    /**
     * Gets credits listener.
     *
     * @return the credits listener
     */
    public ActionListener getCreditsListener() {
        return actionEvent -> this.goCredits();
    }

    /**
     * Gets english listener.
     *
     * @return the english listener
     */
    public ActionListener getEnglishListener() {
        return actionEvent -> this.setLanguage(Language.ENGLISH);
    }

    /**
     * Gets french listener.
     *
     * @return the french listener
     */
    public ActionListener getFrenchListener() {
        return actionEvent -> this.setLanguage(Language.FRENCH);
    }

    /**
     * Gets load game listener.
     *
     * @return the load game listener
     */
    public ActionListener getLoadGameListener() {
        return actionEvent -> this.loadGame();
    }

    /**
     * Gets new game listener.
     *
     * @return the new game listener
     */
    public ActionListener getNewGameListener() {
        return actionEvent -> this.newGame();
    }

    /**
     * Gets one vs ai listener.
     *
     * @return the one vs ai listener
     */
    public ActionListener getOneVsAIListener() {
        return actionEvent -> this.playOneVsAI();
    }

    /**
     * Gets one vs one listener.
     *
     * @return the one vs one listener
     */
    public ActionListener getOneVsOneListener() {
        return actionEvent -> this.playOneVsOne();
    }

    /**
     * Gets play offline listener.
     *
     * @return the play offline listener
     */
    public ActionListener getPlayOfflineListener() {
        return actionEvent -> this.playOffline();
    }

    /**
     * Gets host a game listener.
     *
     * @return the host a game listener
     */
    public ActionListener getHostAGameListener() {
        return actionEvent -> this.hostAGame();
    }

    /**
     * Gets join a game listener.
     *
     * @return the join a game listener
     */
    public ActionListener getJoinAGameListener() {
        return actionEvent -> this.joinAGame();
    }

    /**
     * Gets play online listener.
     *
     * @return the play online listener
     */
    public ActionListener getPlayOnlineListener() {
        return actionEvent -> this.playOnline();
    }

    /**
     * Gets two vs ai listener.
     *
     * @return the two vs ai listener
     */
    public ActionListener getTwoVsAiListener() {
        return actionEvent -> this.playTwoVSAI();
    }

    /**
     * Gets two vs two listener.
     *
     * @return the two vs two listener
     */
    public ActionListener getTwoVsTwoListener() {
        return actionEvent -> this.playTwoVsTwo();
    }
}
