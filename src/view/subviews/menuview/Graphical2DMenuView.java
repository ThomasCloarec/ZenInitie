package view.subviews.menuview;

import controller.menu.Graphic2DMenuController;
import view.Graphical2DView;
import view.subviews.CustomPanel;
import view.subviews.menuview.viewsections.MenuContentSection;
import view.subviews.menuview.viewsections.MenuLeftSection;
import view.subviews.menuview.viewsections.MenuRightSection;

import javax.swing.SwingUtilities;
import java.awt.Window;

/**
 * The type Graphical 2 d menu view.
 */
public class Graphical2DMenuView extends CustomPanel<Graphic2DMenuController, MenuLeftSection, MenuContentSection, MenuRightSection> implements MenuView {
    /**
     * Instantiates a new Graphical 2 d menu view.
     *
     * @param controller the controller
     */
    public Graphical2DMenuView(Graphic2DMenuController controller) {
        super(controller);

        SwingUtilities.invokeLater(() -> {
            this.rightSection = new MenuRightSection(this.controller, this.isHorizontalMode);
            this.contentSection = new MenuContentSection(this.controller, this.isHorizontalMode);
            this.leftSection = new MenuLeftSection(this.controller, this.isHorizontalMode);
        });
    }

    /**
     * Go credits.
     */
    @Override
    public void goCredits() {
        SwingUtilities.invokeLater(() -> this.contentSection.goCredits());
    }

    /**
     * Change language.
     */
    @Override
    public void changeLanguage() {
        SwingUtilities.invokeLater(() -> this.contentSection.changeLanguage());
    }

    /**
     * Change settings.
     */
    @Override
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> this.contentSection.changeSettings());
    }

    /**
     * Exit.
     */
    @Override
    public void exit() {
        Window windowAncestor = SwingUtilities.getWindowAncestor(this);
        //noinspection OverlyStrongTypeCast
        ((Graphical2DView) windowAncestor).close();
    }

    /**
     * Load game.
     */
    @Override
    public void loadGame() {
        SwingUtilities.invokeLater(() -> {

        });
    }

    /**
     * New game.
     */
    @Override
    public void newGame() {
        SwingUtilities.invokeLater(() -> this.contentSection.newGame());
    }

    /**
     * Play offline.
     */
    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> this.contentSection.playOffline());
    }

    /**
     * Play online.
     */
    @Override
    public void playOnline() {
        SwingUtilities.invokeLater(() -> this.contentSection.playOnline());
    }

    /**
     * Go homepage.
     */
    @Override
    public void goHomepage() {
        SwingUtilities.invokeLater(() -> this.contentSection.goHomepage());
    }
}
