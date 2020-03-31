package view.sub_view.graphical_2d;

import controller.menu.MenuController;
import view.sub_view.MenuView;

import java.util.Observable;

public class Graphical2DMenuView extends Graphical2DSubView implements MenuView {
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
    public void playOffline() {
    }

    @Override
    public void playOnline() {
    }

    @Override
    public void start() {
    }

    @Override
    public void update(Observable observable, Object object) {
    }
}
