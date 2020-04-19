package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.sub_views.menu_view.view_sections.sub_panels.ContentPanel;
import view.sub_views.menu_view.view_sections.sub_panels.LeftPanel;
import view.sub_views.menu_view.view_sections.sub_panels.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.util.function.BooleanSupplier;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final BooleanSupplier isHorizontalMode = () -> this.getWidth() > this.getHeight();
    private final MenuController menuController;
    private ContentPanel contentPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public Graphical2DMenuView(MenuController menuController) {
        this.menuController = menuController;

        SwingUtilities.invokeLater(() -> {
            this.setOpaque(false);

            this.rightPanel = new RightPanel(this.menuController, this.isHorizontalMode);
            this.contentPanel = new ContentPanel(this.menuController, this.isHorizontalMode);
            this.leftPanel = new LeftPanel(this.menuController, this.isHorizontalMode);

            this.switchHorizontalMode();
        });
    }

    private void switchHorizontalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 3));
        this.add(this.leftPanel);
        this.add(this.contentPanel);
        this.add(this.rightPanel);
        this.revalidate();
        this.repaint();
    }

    private void switchVerticalMode() {
        this.removeAll();
        this.setLayout(new GridLayout(1, 1));
        this.add(this.contentPanel);
        this.revalidate();
        this.repaint();
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
    public void goHomepage() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.goHomepage();
            this.revalidate();
            this.repaint();
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        this.leftPanel.paintLights(graphics2D);
        this.contentPanel.paintLights(graphics2D);
        this.rightPanel.paintLights(graphics2D);

        if (this.getComponentCount() == 1 && this.isHorizontalMode.getAsBoolean()) {
            this.switchHorizontalMode();
        } else if (this.getComponentCount() == 3 && !this.isHorizontalMode.getAsBoolean()) {
            this.switchVerticalMode();
        }
    }
}
