package view.sub_views.menu_view;

import controller.menu.MenuController;

import javax.swing.*;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final MenuController menuController;

    public Graphical2DMenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void changeLanguage() {
    }

    @Override
    public void changeSettings() {
    }

    @Override
    public void exit() {
    }

    @Override
    public void loadGame() {

    }

    @Override
    public void newGame() {

    }

    @Override
    public void playOffline() {
    }

    @Override
    public void playOnline() {
    }

    @Override
    public void start() {
    }
}
