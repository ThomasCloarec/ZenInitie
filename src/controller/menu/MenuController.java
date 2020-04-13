package controller.menu;

import model.menu.Menu;
import view.utils.text.AppText;
import view.utils.text.Language;

import java.util.function.Consumer;

public class MenuController {
    private final Menu menu;
    private final Consumer<Menu> newGame;

    public MenuController(Menu menu, Consumer<Menu> newGame) {
        this.menu = menu;
        this.newGame = newGame;
    }

    public void backPreviousPage() {
        this.menu.backPreviousPage();
    }

    public void changeLanguage() {
        this.menu.addActualPage(Menu.Page.CHANGE_LANGUAGE);
    }

    public void changeSettings() {
        this.menu.addActualPage(Menu.Page.CHANGE_SETTINGS);
    }

    public void exit() {
        this.menu.exit();
    }

    public void loadGame() {
        this.menu.addActualPage(Menu.Page.LOAD_GAME);
    }

    public void newGame() {
        this.menu.addActualPage(Menu.Page.NEW_GAME);
    }

    public void playOffline() {
        this.menu.setOnlineMode(false);
        this.menu.addActualPage(Menu.Page.PLAY_OFFLINE);
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
        this.menu.addActualPage(Menu.Page.PLAY_ONLINE);
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
