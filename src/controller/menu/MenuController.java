package controller.menu;

import model.menu.Menu;
import model.menu.MenuPage;
import view.utils.text.AppText;
import view.utils.text.Language;

import java.util.function.Consumer;

/**
 * The type Menu controller.
 */
public class MenuController {
    /**
     * The Menu.
     */
    private final Menu menu;
    /**
     * The New game.
     */
    private final Consumer<? super Menu> newGame;

    /**
     * Instantiates a new Menu controller.
     *
     * @param menu    the menu
     * @param newGame the new game
     */
    public MenuController(Menu menu, Consumer<? super Menu> newGame) {
        this.menu = menu;
        this.newGame = newGame;
    }

    /**
     * Back previous page.
     */
    public void backPreviousPage() {
        this.menu.backPreviousPage();
    }

    /**
     * Go credits.
     */
    public void goCredits() {
        this.menu.addActualPage(MenuPage.CREDITS);
    }

    /**
     * Change language.
     */
    public void changeLanguage() {
        this.menu.addActualPage(MenuPage.CHANGE_LANGUAGE);
    }

    /**
     * Change settings.
     */
    public void changeSettings() {
        this.menu.addActualPage(MenuPage.CHANGE_SETTINGS);
    }

    /**
     * Load game.
     */
    public void loadGame() {
        this.menu.addActualPage(MenuPage.LOAD_GAME);
    }

    /**
     * New game.
     */
    public void newGame() {
        this.menu.addActualPage(MenuPage.NEW_GAME);
    }

    /**
     * Play offline.
     */
    public void playOffline() {
        this.menu.setOnlineMode(false);
        this.menu.addActualPage(MenuPage.PLAY_OFFLINE);
    }

    /**
     * Play one vs ai.
     */
    public void playOneVsAI() {
        this.menu.setDuoMode(false);
        this.menu.setAiMode(true);
        this.newGame.accept(this.menu);
    }

    /**
     * Play one vs one.
     */
    public void playOneVsOne() {
        this.menu.setDuoMode(false);
        this.menu.setAiMode(false);
        this.newGame.accept(this.menu);
    }

    /**
     * Play online.
     */
    public void playOnline() {
        this.menu.setOnlineMode(true);
        this.menu.addActualPage(MenuPage.PLAY_ONLINE);
    }

    /**
     * Play two vsai.
     */
    public void playTwoVSAI() {
        this.menu.setDuoMode(true);
        this.menu.setAiMode(true);
        this.newGame.accept(this.menu);
    }

    /**
     * Play two vs two.
     */
    public void playTwoVsTwo() {
        this.menu.setDuoMode(true);
        this.menu.setAiMode(false);
        this.newGame.accept(this.menu);
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(Language language) {
        AppText.setAppLanguage(language);
        this.menu.backPreviousPage();
    }
}
