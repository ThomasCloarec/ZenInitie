package controller.menu;

import model.menu.Menu;
import view.utils.GameText;

import java.util.Locale;

public class MenuController {
    private final Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    public void sendAction(Action action) {
        switch (action) {
            case PLAY_ONLINE:
                this.menu.setOnlineMode(true);
                this.menu.addActualPage(Menu.Page.MENU_PLAY_ONLINE);
                break;
            case PLAY_OFFLINE:
                this.menu.setOnlineMode(false);
                this.menu.addActualPage(Menu.Page.MENU_PLAY_OFFLINE);
                break;
            case CHANGE_SETTINGS:
                this.menu.addActualPage(Menu.Page.MENU_CHANGE_SETTINGS);
                break;
            case CHANGE_LANGUAGE:
                this.menu.addActualPage(Menu.Page.MENU_CHANGE_SETTINGS_CHANGE_LANGUAGE);
                break;
            case SET_ENGLISH:
                GameText.setLocaleLanguage(Locale.ENGLISH);
                this.menu.backPreviousPage();
            case SET_FRENCH:
                GameText.setLocaleLanguage(Locale.FRANCE);
                this.menu.backPreviousPage();
            case BACK_PREVIOUS_PAGE:
                this.menu.backPreviousPage();
                break;
            case EXIT:
                this.menu.exit();
                break;
        }
    }

    public enum Action {
        PLAY_ONLINE,
        CREATE_GAME,
        LAUNCH_GAME,
        JOIN_GAME1,
        JOIN_GAME2,
        JOIN_GAME3,
        PLAY_OFFLINE,
        PLAY_ONE_VS_ONE,
        PLAY_DUO_VS_DUO,
        PLAY_ONE_VS_AI,
        PLAY_DUO_VS_AI,
        CHANGE_SETTINGS,
        CHANGE_LANGUAGE,
        SET_ENGLISH,
        SET_FRENCH,
        BACK_PREVIOUS_PAGE,
        EXIT
    }
}
