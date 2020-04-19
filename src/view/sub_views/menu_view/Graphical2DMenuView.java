package view.sub_views.menu_view;

import controller.menu.MenuController;
import view.sub_views.menu_view.view_sections.MenuContentSection;
import view.sub_views.menu_view.view_sections.MenuLeftSection;
import view.sub_views.menu_view.view_sections.MenuRightSection;
import view.utils.components.CustomPanel;

import javax.swing.*;

public class Graphical2DMenuView extends CustomPanel<MenuController, MenuLeftSection, MenuContentSection, MenuRightSection> implements MenuView {
    public Graphical2DMenuView(MenuController controller) {
        super(controller);

        SwingUtilities.invokeLater(() -> {
            this.rightSection = new MenuRightSection(this.controller, this.isHorizontalMode);
            this.contentSection = new MenuContentSection(this.controller, this.isHorizontalMode);
            this.leftSection = new MenuLeftSection(this.controller, this.isHorizontalMode);
        });
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
}
