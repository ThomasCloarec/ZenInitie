package view.subviews.menuview;

import controller.menu.MenuController;
import view.utils.text.AppText;
import view.utils.text.Language;
import view.utils.text.TextInput;

/**
 * The type Textual menu view.
 */
public class TextualMenuView implements MenuView {
    /**
     * The Menu controller.
     */
    private final MenuController menuController;

    /**
     * Instantiates a new Textual menu view.
     *
     * @param menuController the menu controller
     */
    public TextualMenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    /**
     * Go credits.
     */
    @Override
    public void goCredits() {
        System.out.println(AppText.getTextFor("menu.credits"));
        System.out.println("Credits :");
        System.out.println(AppText.preInformation + AppText.getTextFor("menu.credits.hugo"));
        System.out.println(AppText.preInformation + AppText.getTextFor("menu.credits.valentin"));
        int input = TextInput.getMenuAnswer("menu.credits.question1");
        if (input == 1) {
            this.menuController.backPreviousPage();
        }
    }

    /**
     * Change language.
     */
    @Override
    public void changeLanguage() {
        int input = TextInput.getMenuAnswer("menu.settings.language.question1");
        switch (input) {
            case 1:
                this.menuController.setLanguage(Language.ENGLISH);
                break;
            case 2:
                this.menuController.setLanguage(Language.FRENCH);
                break;
            case 3:
                this.menuController.backPreviousPage();
                break;
        }
    }

    /**
     * Change settings.
     */
    @Override
    public void changeSettings() {
        int input = TextInput.getMenuAnswer("menu.settings.question1");
        switch (input) {
            case 1:
                this.menuController.changeLanguage();
                break;
            case 2:
                this.menuController.goCredits();
                break;
            case 3:
                this.menuController.backPreviousPage();
                break;
        }
    }

    /**
     * Exit.
     */
    @Override
    public void exit() {
        System.out.println(AppText.preInformation + AppText.getTextFor("global.exit"));
        System.exit(0);
    }

    /**
     * Load game.
     */
    @Override
    public void loadGame() {
        int input = TextInput.getMenuAnswer("menu.offline.loadGame.question1");
        if (input == 1) {
            this.menuController.backPreviousPage();
        }
    }

    /**
     * New game.
     */
    @Override
    public void newGame() {
        int input = TextInput.getMenuAnswer("menu.offline.newGame.question1");
        switch (input) {
            case 1:
                this.menuController.playOneVsOne();
                break;
            case 2:
                this.menuController.playOneVsAI();
                break;
            case 3:
                this.menuController.playTwoVsTwo();
                break;
            case 4:
                this.menuController.playTwoVSAI();
                break;
            case 5:
                this.menuController.backPreviousPage();
                break;
        }
    }

    /**
     * Play offline.
     */
    @Override
    public void playOffline() {
        int input = TextInput.getMenuAnswer("menu.offline.question1");
        switch (input) {
            case 1:
                this.menuController.newGame();
                break;
            case 2:
                this.menuController.loadGame();
                break;
            case 3:
                this.menuController.backPreviousPage();
                break;
        }
    }

    /**
     * Play online.
     */
    @Override
    public void playOnline() {
        int input = TextInput.getMenuAnswer("menu.online.question1");
        switch (input) {
            case 1:
                this.menuController.hostAGame();
                break;
            case 2:
                this.menuController.joinAGame();
                break;
            case 3:
                this.menuController.backPreviousPage();
                break;
        }
    }

    /**
     * Go homepage.
     */
    @Override
    public void goHomepage() {
        int input = TextInput.getMenuAnswer("menu.question1");
        switch (input) {
            case 1:
                this.menuController.playOnline();
                break;
            case 2:
                this.menuController.playOffline();
                break;
            case 3:
                this.menuController.changeSettings();
                break;
            case 4:
                this.menuController.backPreviousPage();
                break;
        }
    }

    @Override
    public void hostAGame() {
        int input = TextInput.getMenuAnswer("menu.offline.newGame.question1");
        switch (input) {
            case 1:
                this.menuController.playOneVsOne();
                break;
            case 2:
                this.menuController.playTwoVsTwo();
                break;
            case 3:
                this.menuController.playTwoVSAI();
                break;
            case 4:
                this.menuController.backPreviousPage();
                break;
        }
    }

    @Override
    public void goLobby() {

    }
}
