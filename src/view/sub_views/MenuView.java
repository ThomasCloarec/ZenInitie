package view.sub_views;

import java.util.Observer;

public interface MenuView extends Observer {
    void changeLanguage();

    void changeSettings();

    void exit();

    void loadGame();

    void newGame();

    void playOffline();

    void playOnline();

    void start();
}
