package view.subviews.menuview;

import model.menu.MenuPage;
import utils.observer.Observer;

/**
 * The interface Menu view.
 */
public interface MenuView extends Observer<MenuView> {
    /**
     * Go credits.
     */
    void goCredits();

    /**
     * Change language.
     */
    void changeLanguage();

    /**
     * Change settings.
     */
    void changeSettings();

    /**
     * Exit.
     */
    void exit();

    /**
     * Load game.
     */
    void loadGame();

    /**
     * New game.
     */
    void newGame();

    /**
     * Play offline.
     */
    void playOffline();

    /**
     * Play online.
     */
    void playOnline();

    /**
     * Go homepage.
     */
    void goHomepage();

    void hostAGame();

    void goLobby();

    /**
     * Update page.
     *
     * @param menuPage the menu page
     */
    default void updatePage(MenuPage menuPage) {
        switch (menuPage) {
            case ROOT:
                this.goHomepage();
                break;
            case PLAY_ONLINE:
                this.playOnline();
                break;
            case PLAY_OFFLINE:
                this.playOffline();
                break;
            case HOST_A_GAME:
                this.hostAGame();
                break;
            case LOBBY:
                this.goLobby();
                break;
            case NEW_GAME:
                this.newGame();
                break;
            case LOAD_GAME:
                this.loadGame();
                break;
            case CHANGE_SETTINGS:
                this.changeSettings();
                break;
            case CHANGE_LANGUAGE:
                this.changeLanguage();
                break;
            case CREDITS:
                this.goCredits();
                break;
        }
    }
}
