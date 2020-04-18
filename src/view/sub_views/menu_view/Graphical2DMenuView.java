package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.sub_views.menu_view.view_sections.ContentPanel;
import view.utils.AppColor;
import view.utils.components.ImageComponent;
import view.utils.components.LightComponent;
import view.utils.components.ScaledImageComponent;

import javax.swing.*;
import java.awt.*;
import java.util.function.BooleanSupplier;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final MenuController menuController;
    private ImageComponent blueDragon;
    private ContentPanel contentPanel;
    private ImageComponent redDragon;

    public Graphical2DMenuView(MenuController menuController) {
        this.menuController = menuController;

        SwingUtilities.invokeLater(() -> {
            BooleanSupplier isHorizontalMode = () -> this.getWidth() > this.getHeight();

            this.redDragon = new ScaledImageComponent("red_dragon.png", 0.25, 0.7, this);
            this.redDragon.setVisibleCondition(isHorizontalMode);

            this.contentPanel = new ContentPanel(this.menuController, new ScaledImageComponent("logo_zen.png", 0.3, this));

            this.blueDragon = new ScaledImageComponent("blue_dragon.png", this.redDragon, false);
            this.blueDragon.setVisibleCondition(isHorizontalMode);

            this.setBackground(AppColor.CUSTOM_GREY);
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

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
            this.contentPanel.changeLanguage();
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.changeSettings();
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
            this.contentPanel.newGame();
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        Point center = this.contentPanel.getLogoZenCenter();
        float radius = this.getWidth() / 2f;
        new LightComponent(center, radius, AppColor.CUSTOM_LIGHT_GREY).paint(graphics2D);

        if (this.getWidth() > this.getHeight()) {
            center = new Point((int) (this.getWidth() * -0.2), this.getHeight() / 2);
            radius = this.getWidth() / 3f + this.getWidth() * 0.4f;
            new LightComponent(center, radius, AppColor.CUSTOM_BLUE).paint(graphics2D);

            center = new Point((int) (this.getWidth() * 1.2), this.getHeight() / 2);
            new LightComponent(center, radius, AppColor.CUSTOM_RED).paint(graphics2D);
        }
    }

    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.playOffline();
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
            this.contentPanel.start();
            this.revalidate();
            this.repaint();
        });
    }
}
