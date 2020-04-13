package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.utils.ImageComponent;

import javax.swing.*;
import java.awt.*;

import static view.utils.AppText.getTextFor;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final MenuController menuController;

    public Graphical2DMenuView(MenuController menuController) {
        this.menuController = menuController;
        this.setBackground(new Color(44, 47, 51));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        this.add(Box.createVerticalGlue());
        JLabel label = new ImageComponent("logo_zen.png", 100).getAsLabel();
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        for (int i = 1; i <= 4; i++) {
            JButton button = new JButton(getTextFor("menu.question1.answer" + i));
            button.setAlignmentX(JButton.CENTER_ALIGNMENT);
            this.add(button);
            this.add(Box.createRigidArea(new Dimension(0, 20)));
        }
        this.add(Box.createVerticalGlue());
        this.revalidate();
    }
}
