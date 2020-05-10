package controller.menu;

import model.menu.Menu;
import view.Graphical2DView;
import view.utils.text.AppText;
import view.utils.text.Language;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.function.Consumer;

public class Graphic2DMenuController extends MenuController {
    public Graphic2DMenuController(Menu menu, Consumer<? super Menu> newGame) {
        super(menu, newGame);
    }

    public static ActionListener getToggleFullScreenListener(Graphical2DView frame) {
        return actionEvent -> frame.toggleFullScreen();
    }

    public static ComponentAdapter getButtonFullScreenResized(Graphical2DView frame, JButton button) {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                super.componentResized(componentEvent);
                if (frame.isFullscreenModeActivated()) {
                    button.setText(AppText.getTextFor("global.fullscreen.stop"));
                } else {
                    button.setText(AppText.getTextFor("global.fullscreen.start"));
                }
            }
        };
    }

    public ActionListener getBackPreviousPageListener() {
        return actionEvent -> this.backPreviousPage();
    }

    public ActionListener getChangeLanguageListener() {
        return actionEvent -> this.changeLanguage();
    }

    public ActionListener getChangeSettingsListener() {
        return actionEvent -> this.changeSettings();
    }

    public ActionListener getCreditsListener() {
        return actionEvent -> this.goCredits();
    }

    public ActionListener getEnglishListener() {
        return actionEvent -> this.setLanguage(Language.ENGLISH);
    }

    public ActionListener getFrenchListener() {
        return actionEvent -> this.setLanguage(Language.FRENCH);
    }

    public ActionListener getLoadGameListener() {
        return actionEvent -> this.loadGame();
    }

    public ActionListener getNewGameListener() {
        return actionEvent -> this.newGame();
    }

    public ActionListener getOneVsAIListener() {
        return actionEvent -> this.playOneVsAI();
    }

    public ActionListener getOneVsOneListener() {
        return actionEvent -> this.playOneVsOne();
    }

    public ActionListener getPlayOfflineListener() {
        return actionEvent -> this.playOffline();
    }

    public ActionListener getPlayOnlineListener() {
        return actionEvent -> this.playOnline();
    }

    public ActionListener getTwoVsAiListener() {
        return actionEvent -> this.playTwoVSAI();
    }

    public ActionListener getTwoVsTwoListener() {
        return actionEvent -> this.playTwoVsTwo();
    }
}
