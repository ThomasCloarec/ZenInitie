package view.sub_views;

import java.util.Observer;

public interface MenuView extends Observer {
    void changeLanguage();

    void changeSettings();

    void exit();

    void playOffline();

    void playOnline();

    void start();
}
