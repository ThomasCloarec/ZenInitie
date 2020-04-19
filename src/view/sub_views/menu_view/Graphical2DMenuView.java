package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.sub_views.menu_view.view_sections.MenuContentSection;
import view.sub_views.menu_view.view_sections.MenuLeftSection;
import view.sub_views.menu_view.view_sections.MenuRightSection;

import javax.swing.*;
import java.awt.*;
import java.util.function.BooleanSupplier;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final BooleanSupplier isHorizontalMode = () -> this.getWidth() > this.getHeight();
    private final MenuController menuController;
    private MenuContentSection contentSection;
    private MenuLeftSection leftSection;
    private MenuRightSection rightSection;

    public Graphical2DMenuView(MenuController menuController) {
        this.menuController = menuController;

        SwingUtilities.invokeLater(() -> {
            this.setOpaque(false);

            this.rightSection = new MenuRightSection(this.menuController, this.isHorizontalMode);
            this.contentSection = new MenuContentSection(this.menuController, this.isHorizontalMode);
            this.leftSection = new MenuLeftSection(this.menuController, this.isHorizontalMode);

            this.switchHorizontalMode();
        });
    }

    private void switchHorizontalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 3));
        this.add(this.leftSection);
        this.add(this.contentSection);
        this.add(this.rightSection);
        this.revalidate();
        this.repaint();
    }

    private void switchVerticalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 1));
        this.add(this.contentSection);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void changeLanguage() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.changeLanguage();
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.changeSettings();
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
        SwingUtilities.invokeLater(() -> {

        });
    }

    @Override
    public void newGame() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.newGame();
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.playOffline();
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void playOnline() {
        SwingUtilities.invokeLater(() -> {

        });
    }

    @Override
    public void goHomepage() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.goHomepage();
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        this.leftSection.paintLights(graphics2D);
        this.contentSection.paintLights(graphics2D);
        this.rightSection.paintLights(graphics2D);

        if (this.getComponentCount() == 1 && this.isHorizontalMode.getAsBoolean()) {
            this.switchHorizontalMode();
        } else if (this.getComponentCount() == 3 && !this.isHorizontalMode.getAsBoolean()) {
            this.switchVerticalMode();
        }
    }
}
