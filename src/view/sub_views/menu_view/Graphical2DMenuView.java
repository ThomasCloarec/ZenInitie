package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.utils.Color;
import view.utils.components.ButtonComponent;
import view.utils.components.ImageComponent;

import javax.swing.*;
import java.awt.*;

import static view.utils.text.AppText.getTextFor;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final JLabel blueDragon = new ImageComponent("blue_dragon.png", 430, 593).getAsLabel();
    private final JLabel redDragon = new ImageComponent("red_dragon.png", 430, 593).getAsLabel();
    private JPanel contentPanel;
    private MenuController menuController;

    public Graphical2DMenuView(MenuController menuController) {
        SwingUtilities.invokeLater(() -> {
            this.menuController = menuController;
            this.setBackground(Color.DISCORD_GREY);
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.contentPanel = new JPanel();
            this.contentPanel.setBackground(Color.DISCORD_GREY);
            this.contentPanel.setLayout(new BoxLayout(this.contentPanel, BoxLayout.Y_AXIS));

            this.add(Box.createHorizontalGlue());
            this.add(this.blueDragon);
            this.add(Box.createHorizontalGlue());
            this.add(this.contentPanel);
            this.add(Box.createHorizontalGlue());
            this.add(this.redDragon);
            this.add(Box.createHorizontalGlue());
        });
    }

    @Override
    public void changeLanguage() {
    }

    @Override
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.removeAll();

            this.contentPanel.add(Box.createVerticalGlue());
            this.contentPanel.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.contentPanel.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.settings.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.settings.question1.answer2"));

            button1.addActionListener(actionEvent -> this.menuController.changeLanguage());
            button2.addActionListener(actionEvent -> this.menuController.backPreviousPage());

            this.contentPanel.add(button1);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button2);
            this.contentPanel.add(Box.createVerticalGlue());

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
            this.contentPanel.removeAll();

            this.contentPanel.add(Box.createVerticalGlue());
            this.contentPanel.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.contentPanel.add(Box.createVerticalGlue());

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

            this.contentPanel.add(button1);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button2);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button3);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button4);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button5);
            this.contentPanel.add(Box.createVerticalGlue());

            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (this.getSize().width < 1400) {
            this.blueDragon.setVisible(false);
            this.redDragon.setVisible(false);
        } else {
            this.blueDragon.setVisible(true);
            this.redDragon.setVisible(true);
        }
    }

    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.removeAll();

            this.contentPanel.add(Box.createVerticalGlue());
            this.contentPanel.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.contentPanel.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.offline.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.offline.question1.answer2"));
            JButton button3 = new ButtonComponent(getTextFor("menu.offline.question1.answer3"));

            button1.addActionListener(actionEvent -> this.menuController.newGame());
            button2.addActionListener(actionEvent -> this.menuController.loadGame());
            button3.addActionListener(actionEvent -> this.menuController.backPreviousPage());

            this.contentPanel.add(button1);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button2);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button3);
            this.contentPanel.add(Box.createVerticalGlue());

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
            this.contentPanel.removeAll();

            this.contentPanel.add(Box.createVerticalGlue());
            this.contentPanel.add(new ImageComponent("logo_zen.png", 250).getAsLabel());
            this.contentPanel.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.question1.answer2"));
            JButton button3 = new ButtonComponent(getTextFor("menu.question1.answer3"));
            JButton button4 = new ButtonComponent(getTextFor("menu.question1.answer4"));

            button1.addActionListener(actionEvent -> this.menuController.playOnline());
            button2.addActionListener(actionEvent -> this.menuController.playOffline());
            button3.addActionListener(actionEvent -> this.menuController.changeSettings());
            button4.addActionListener(actionEvent -> this.menuController.exit());

            this.contentPanel.add(button1);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button2);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button3);
            this.contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));

            this.contentPanel.add(button4);
            this.contentPanel.add(Box.createVerticalGlue());

            this.revalidate();
            this.repaint();
        });
    }
}
