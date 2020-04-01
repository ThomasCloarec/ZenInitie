package view.sub_views.textual;

import controller.menu.MenuController;
import model.menu.Menu;
import view.sub_views.MenuView;
import view.utils.GameText;
import view.utils.TextInput;

import java.util.Observable;

public class TextualMenuView extends TextualSubView implements MenuView {
    private final MenuController menuController;

    public TextualMenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void changeLanguage() {
        int input = TextInput.getAnswer("menu.settings.language.question1", 3);
        switch (input) {
            case 1:
                this.menuController.sendAction(MenuController.Action.SET_ENGLISH);
                break;
            case 2:
                this.menuController.sendAction(MenuController.Action.SET_FRENCH);
                break;
            case 3:
                this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
                break;
        }
    }

    @Override
    public void changeSettings() {
        int input = TextInput.getAnswer("menu.settings.question1", 2);
        switch (input) {
            case 1:
                this.menuController.sendAction(MenuController.Action.CHANGE_LANGUAGE);
                break;
            case 2:
                this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
                break;
        }
    }

    @Override
    public void exit() {
        System.out.println(GameText.preInformation + GameText.get("exit"));
    }

    @Override
    public void loadGame() {
        int input = TextInput.getAnswer("menu.offline.loadGame.question1", 1);
        if (input == 1) {
            this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
        }
    }

    @Override
    public void newGame() {
        int input = TextInput.getAnswer("menu.offline.newGame.question1", 5);
        switch (input) {
            case 1:
                this.menuController.sendAction(MenuController.Action.PLAY_ONE_VS_ONE);
                break;
            case 2:
                this.menuController.sendAction(MenuController.Action.PLAY_ONE_VS_AI);
                break;
            case 3:
                this.menuController.sendAction(MenuController.Action.PLAY_TWO_VS_TWO);
                break;
            case 4:
                this.menuController.sendAction(MenuController.Action.PLAY_TWO_VS_AI);
                break;
            case 5:
                this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
                break;
        }
    }

    @Override
    public void playOffline() {
        int input = TextInput.getAnswer("menu.offline.question1", 3);
        switch (input) {
            case 1:
                this.menuController.sendAction(MenuController.Action.NEW_GAME);
                break;
            case 2:
                this.menuController.sendAction(MenuController.Action.LOAD_GAME);
                break;
            case 3:
                this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
                break;
        }
    }

    @Override
    public void playOnline() {
        int input = TextInput.getAnswer("menu.online.question1", 1);
        if (input == 1) {
            this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
        }
    }

    @Override
    public void start() {
        int input = TextInput.getAnswer("menu.question1", 4);
        switch (input) {
            case 1:
                this.menuController.sendAction(MenuController.Action.PLAY_ONLINE);
                break;
            case 2:
                this.menuController.sendAction(MenuController.Action.PLAY_OFFLINE);
                break;
            case 3:
                this.menuController.sendAction(MenuController.Action.CHANGE_SETTINGS);
                break;
            case 4:
                this.menuController.sendAction(MenuController.Action.EXIT);
                break;
        }
    }

    @Override
    public void update(Observable observable, Object object) {
        Menu menu = (Menu) observable;
        if (object.equals(Menu.UpdateNotification.UPDATE_PAGE)) {
            Menu.Page actualPage = menu.getActualPage();
            switch (actualPage) {
                case ROOT:
                    this.start();
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
            }
        } else if (object.equals(Menu.UpdateNotification.EXIT)) {
            this.exit();
        }
    }
}
