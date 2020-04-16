package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.utils.AppColor;
import view.utils.components.ButtonComponent;
import view.utils.components.ScaledImageComponent;
import view.utils.text.Language;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

import static view.utils.text.AppText.getTextFor;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final JPanel logoZen = new ScaledImageComponent("logo_zen.png", 0.3, this);
    private final JPanel redDragon = new ScaledImageComponent("red_dragon.png", 0.25, 0.7, this);
    private final JPanel blueDragon = new ScaledImageComponent("blue_dragon.png", this.redDragon, false);
    private JPanel contentPanel;
    private MenuController menuController;

    public Graphical2DMenuView(MenuController menuController) {
        SwingUtilities.invokeLater(() -> {
            this.menuController = menuController;
            this.setBackground(AppColor.DISCORD_GREY);
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            this.contentPanel = new JPanel();
            this.contentPanel.setOpaque(false);
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
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.removeAll();

            this.contentPanel.add(Box.createVerticalGlue());
            this.contentPanel.add(this.logoZen);
            this.contentPanel.add(Box.createVerticalGlue());

            JButton button1 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer1"));
            JButton button2 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer2"));
            JButton button3 = new ButtonComponent(getTextFor("menu.settings.language.question1.answer3"));

            button1.addActionListener(actionEvent -> this.menuController.setLanguage(Language.ENGLISH));
            button2.addActionListener(actionEvent -> this.menuController.setLanguage(Language.FRENCH));
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
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.removeAll();

            this.contentPanel.add(Box.createVerticalGlue());
            this.contentPanel.add(this.logoZen);
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
            this.contentPanel.add(this.logoZen);
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

        Graphics2D graphics2D = (Graphics2D) graphics;

        float[] dist = {0f, 1f};
        Point2D center = new Point2D.Float(this.contentPanel.getX() + this.logoZen.getX() + this.logoZen.getWidth() / 2f, this.contentPanel.getY() + this.logoZen.getY() + this.logoZen.getHeight() / 2f);
        float radius = this.getWidth() / 6f;
        Color[] colors = {AppColor.CUSTOM_LIGHT_GREY, AppColor.DISCORD_GREY};
        graphics2D.setPaint(new RadialGradientPaint(center, radius, dist, colors));
        graphics2D.fillOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) radius * 2, (int) radius * 2);

        radius = (float) Math.hypot(center.getX(), this.getHeight() / 2f - center.getY()) - radius;
        center = new Point2D.Float(0, this.getHeight() / 2f);
        colors = new Color[]{AppColor.CUSTOM_BLUE, AppColor.DISCORD_GREY};
        graphics2D.setPaint(new RadialGradientPaint(center, radius, dist, colors));
        graphics2D.fillOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) radius * 2, (int) radius * 2);

        center = new Point2D.Float(this.getWidth(), this.getHeight() / 2f);
        colors = new Color[]{AppColor.CUSTOM_RED, AppColor.DISCORD_GREY};
        graphics2D.setPaint(new RadialGradientPaint(center, radius, dist, colors));
        graphics2D.fillOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) radius * 2, (int) radius * 2);
    }

    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.removeAll();

            this.contentPanel.add(Box.createVerticalGlue());
            this.contentPanel.add(this.logoZen);
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
            this.contentPanel.add(this.logoZen);
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
