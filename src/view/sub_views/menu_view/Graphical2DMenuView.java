package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.utils.components.ButtonComponent;
import view.utils.components.ImageComponent;

import javax.swing.*;
import java.awt.*;

import static view.utils.text.AppText.getTextFor;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private MenuController menuController;

    public Graphical2DMenuView(MenuController menuController) {
        SwingUtilities.invokeLater(() -> {
            this.menuController = menuController;
            this.setBackground(new Color(44, 47, 51));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        });
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
        SwingUtilities.invokeLater(() -> {
            this.add(Box.createVerticalGlue());

            this.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.add(Box.createRigidArea(new Dimension(0, 50)));

            this.add(new ButtonComponent(getTextFor("menu.question1.answer1")));
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(new ButtonComponent(getTextFor("menu.question1.answer2")));
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(new ButtonComponent(getTextFor("menu.question1.answer3")));
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(new ButtonComponent(getTextFor("menu.question1.answer4")));
            this.add(Box.createVerticalGlue());

            this.revalidate();
        });
    }
}
