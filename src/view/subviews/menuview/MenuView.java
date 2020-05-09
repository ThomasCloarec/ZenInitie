package view.subviews.menuview;

import model.menu.Menu;
import model.menu.MenuPage;

public interface MenuView {
    void goCredits();

    void changeLanguage();

    void changeSettings();

    void exit();

    void loadGame();

    void newGame();

    void playOffline();

    void playOnline();

    void goHomepage();

    default void updatePage(Menu menu) {
        MenuPage actualPage = menu.getActualPage();
        switch (actualPage) {
            case ROOT:
                this.goHomepage();
                break;
            case PLAY_ONLINE:
                this.playOnline();
                break;
            case PLAY_OFFLINE:
                this.playOffline();
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
