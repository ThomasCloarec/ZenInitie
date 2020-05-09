package view.subviews.menuview;

import controller.menu.MenuController;
import view.subviews.CustomPanel;
import view.subviews.menuview.viewsections.MenuContentSection;
import view.subviews.menuview.viewsections.MenuLeftSection;
import view.subviews.menuview.viewsections.MenuRightSection;

import javax.swing.SwingUtilities;
import java.awt.Window;

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
    public void goCredits() {
        SwingUtilities.invokeLater(() -> this.contentSection.goCredits());
    }

    @Override
    public void changeLanguage() {
        SwingUtilities.invokeLater(() -> this.contentSection.changeLanguage());
    }

    @Override
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> this.contentSection.changeSettings());
    }

    @Override
    public void exit() {
        Window windowAncestor = SwingUtilities.getWindowAncestor(this);
        windowAncestor.dispose();
        System.exit(0);
    }

    @Override
    public void loadGame() {
        SwingUtilities.invokeLater(() -> {

        });
    }

    @Override
    public void newGame() {
        SwingUtilities.invokeLater(() -> this.contentSection.newGame());
    }

    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> this.contentSection.playOffline());
    }

    @Override
    public void playOnline() {
        SwingUtilities.invokeLater(() -> {

        });
    }

    @Override
    public void goHomepage() {
        SwingUtilities.invokeLater(() -> this.contentSection.goHomepage());
    }
}
