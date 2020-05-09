package controller.menu;

import model.menu.Menu;
import model.menu.MenuPage;
import view.utils.text.AppText;
import view.utils.text.Language;

import java.util.function.Consumer;

public class MenuController {
    private final Menu menu;
    private final Consumer<? super Menu> newGame;

    public MenuController(Menu menu, Consumer<? super Menu> newGame) {
        this.menu = menu;
        this.newGame = newGame;
    }

    public void backPreviousPage() {
        this.menu.backPreviousPage();
    }

    public void goCredits() {
        this.menu.addActualPage(MenuPage.CREDITS);
    }

    public void changeLanguage() {
        this.menu.addActualPage(MenuPage.CHANGE_LANGUAGE);
    }

    public void changeSettings() {
        this.menu.addActualPage(MenuPage.CHANGE_SETTINGS);
    }

    public void loadGame() {
        this.menu.addActualPage(MenuPage.LOAD_GAME);
    }

    public void newGame() {
        this.menu.addActualPage(MenuPage.NEW_GAME);
    }

    public void playOffline() {
        this.menu.setOnlineMode(false);
        this.menu.addActualPage(MenuPage.PLAY_OFFLINE);
    }

    public void playOneVsAI() {
        this.menu.setDuoMode(false);
        this.menu.setAiMode(true);
        this.newGame.accept(this.menu);
    }

    public void playOneVsOne() {
        this.menu.setDuoMode(false);
        this.menu.setAiMode(false);
        this.newGame.accept(this.menu);
    }

    public void playOnline() {
        this.menu.setOnlineMode(true);
        this.menu.addActualPage(MenuPage.PLAY_ONLINE);
    }

    public void playTwoVSAI() {
        this.menu.setDuoMode(true);
        this.menu.setAiMode(true);
        this.newGame.accept(this.menu);
    }

    public void playTwoVsTwo() {
        this.menu.setDuoMode(true);
        this.menu.setAiMode(false);
        this.newGame.accept(this.menu);
    }

    public void setLanguage(Language language) {
        AppText.setAppLanguage(language);
        this.menu.backPreviousPage();
    }
}
