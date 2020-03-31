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
        if (input == 1) {
            this.menuController.sendAction(MenuController.Action.SET_ENGLISH);
        } else if (input == 2) {
            this.menuController.sendAction(MenuController.Action.SET_FRENCH);
        } else if (input == 3) {
            this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
        }
    }

    @Override
    public void changeSettings() {
        int input = TextInput.getAnswer("menu.settings.question1", 2);
        if (input == 1) {
            this.menuController.sendAction(MenuController.Action.CHANGE_LANGUAGE);
        } else if (input == 2) {
            this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
        }
    }

    @Override
    public void exit() {
        System.out.println(GameText.preInformation + GameText.get("exit"));
    }

    @Override
    public void playOffline() {
        int input = TextInput.getAnswer("menu.offline.question1", 1);
        if (input == 1) {
            this.menuController.sendAction(MenuController.Action.BACK_PREVIOUS_PAGE);
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
        if (input == 1) {
            this.menuController.sendAction(MenuController.Action.PLAY_ONLINE);
        } else if (input == 2) {
            this.menuController.sendAction(MenuController.Action.PLAY_OFFLINE);
        } else if (input == 3) {
            this.menuController.sendAction(MenuController.Action.CHANGE_SETTINGS);
        } else if (input == 4) {
            this.menuController.sendAction(MenuController.Action.EXIT);
        }
    }

    @Override
    public void update(Observable observable, Object object) {
        Menu menu = (Menu) observable;
        if (object.equals(Menu.UpdateNotification.UPDATE_PAGE)) {
            Menu.Page actualPage = menu.getActualPage();
            if (actualPage == Menu.Page.MENU) {
                this.start();
            } else if (actualPage == Menu.Page.MENU_PLAY_ONLINE) {
                this.playOnline();
            } else if (actualPage == Menu.Page.MENU_PLAY_OFFLINE) {
                this.playOffline();
            } else if (actualPage == Menu.Page.MENU_CHANGE_SETTINGS) {
                this.changeSettings();
            } else if (actualPage == Menu.Page.MENU_CHANGE_SETTINGS_CHANGE_LANGUAGE) {
                this.changeLanguage();
            }
        } else if (object.equals(Menu.UpdateNotification.EXIT)) {
            this.exit();
        }
    }
}
