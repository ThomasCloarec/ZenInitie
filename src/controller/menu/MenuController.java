package controller.menu;

import model.game.GameData;
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
     * The Cancel network lobby.
     */
    private final Runnable cancelNetworkLobby;
    /**
     * The Play loaded game.
     */
    private final Consumer<GameData> playLoadedGame;

    /**
     * Instantiates a new Menu controller.
     *
     * @param menu               the menu
     * @param newGame            the new game
     * @param playLoadedGame     the play loaded game
     * @param cancelNetworkLobby the cancel network lobby
     */
    public MenuController(Menu menu, Consumer<? super Menu> newGame, Consumer<GameData> playLoadedGame, Runnable cancelNetworkLobby) {
        this.menu = menu;
        this.newGame = newGame;
        this.playLoadedGame = playLoadedGame;
        this.cancelNetworkLobby = cancelNetworkLobby;
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
     * Host a game.
     */
    public void hostAGame() {
        this.menu.setOnlineClient(false);
        this.menu.setOnlineServer(true);
        this.menu.addActualPage(MenuPage.HOST_A_GAME);
    }

    /**
     * Join a game.
     */
    public void joinAGame() {
        this.menu.setOnlineClient(true);
        this.menu.setOnlineServer(false);
        this.newGame.accept(this.menu);
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
        this.menu.playOffline();
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

    /**
     * Cancel network lobby.
     */
    public void cancelNetworkLobby() {
        this.cancelNetworkLobby.run();
        this.menu.backPreviousPage();
    }

    /**
     * Play loaded game.
     *
     * @param gameData the game data
     */
    public void playLoadedGame(GameData gameData) {
        this.playLoadedGame.accept(gameData);
    }
}
