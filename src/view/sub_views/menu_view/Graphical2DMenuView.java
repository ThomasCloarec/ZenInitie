package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.sub_views.menu_view.view_sections.sub_panels.ContentPanel;
import view.sub_views.menu_view.view_sections.sub_panels.LeftPanel;
import view.sub_views.menu_view.view_sections.sub_panels.RightPanel;
import view.utils.AppColor;

import javax.swing.*;
import java.awt.*;
import java.util.function.BooleanSupplier;

public class Graphical2DMenuView extends JPanel implements MenuView {
    private final MenuController menuController;
    private ContentPanel contentPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public Graphical2DMenuView(MenuController menuController) {
        this.menuController = menuController;

        SwingUtilities.invokeLater(() -> {
            BooleanSupplier isHorizontalMode = () -> this.getWidth() > this.getHeight();

            this.rightPanel = new RightPanel();
            this.rightPanel.setHorizontalMode(isHorizontalMode);

            this.contentPanel = new ContentPanel(this.menuController);
            this.contentPanel.setHorizontalMode(isHorizontalMode);

            this.leftPanel = new LeftPanel();
            this.leftPanel.setHorizontalMode(isHorizontalMode);

            this.setBackground(AppColor.CUSTOM_GREY);
            this.setLayout(new GridLayout(1, 3));

            this.add(this.leftPanel);
            this.add(this.contentPanel);
            this.add(this.rightPanel);

            this.revalidate();
            this.repaint();
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
    public void goHomepage() {
        SwingUtilities.invokeLater(() -> {
            this.contentPanel.goHomepage();
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
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        this.leftPanel.paintLights(graphics2D);
        this.contentPanel.paintLights(graphics2D);
        this.rightPanel.paintLights(graphics2D);
    }
}
