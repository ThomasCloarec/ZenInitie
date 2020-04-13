package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.utils.Color;
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
            this.setBackground(Color.DISCORD_GREY);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        });
    }

    @Override
    public void changeLanguage() {
    }

    @Override
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();

            this.add(Box.createVerticalGlue());
            this.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.settings.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.settings.question1.answer2"));

            button1.addActionListener(actionEvent -> this.menuController.changeLanguage());
            button2.addActionListener(actionEvent -> this.menuController.backPreviousPage());

            this.add(button1);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button2);
            this.add(Box.createVerticalGlue());

            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void loadGame() {

    }

    @Override
    public void newGame() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();

            this.add(Box.createVerticalGlue());
            this.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer2"));
            JButton button3 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer3"));
            JButton button4 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer4"));
            JButton button5 = new ButtonComponent(getTextFor("menu.offline.newGame.question1.answer5"));

            button1.addActionListener(actionEvent -> this.menuController.playOneVsOne());
            button2.addActionListener(actionEvent -> this.menuController.playOneVsAI());
            button3.addActionListener(actionEvent -> this.menuController.playTwoVsTwo());
            button4.addActionListener(actionEvent -> this.menuController.playTwoVSAI());
            button5.addActionListener(actionEvent -> this.menuController.backPreviousPage());

            this.add(button1);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button2);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button3);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button4);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button5);
            this.add(Box.createVerticalGlue());

            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();

            this.add(Box.createVerticalGlue());
            this.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.offline.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.offline.question1.answer2"));
            JButton button3 = new ButtonComponent(getTextFor("menu.offline.question1.answer3"));

            button1.addActionListener(actionEvent -> this.menuController.newGame());
            button2.addActionListener(actionEvent -> this.menuController.loadGame());
            button3.addActionListener(actionEvent -> this.menuController.backPreviousPage());

            this.add(button1);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button2);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button3);
            this.add(Box.createVerticalGlue());

            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void playOnline() {

    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();

            this.add(Box.createVerticalGlue());
            this.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.question1.answer2"));
            JButton button3 = new ButtonComponent(getTextFor("menu.question1.answer3"));
            JButton button4 = new ButtonComponent(getTextFor("menu.question1.answer4"));

            button1.addActionListener(actionEvent -> this.menuController.playOnline());
            button2.addActionListener(actionEvent -> this.menuController.playOffline());
            button3.addActionListener(actionEvent -> this.menuController.changeSettings());
            button4.addActionListener(actionEvent -> this.menuController.exit());

            this.add(button1);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button2);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button3);
            this.add(Box.createRigidArea(new Dimension(0, 25)));

            this.add(button4);
            this.add(Box.createVerticalGlue());

            this.revalidate();
            this.repaint();
        });
    }
}
