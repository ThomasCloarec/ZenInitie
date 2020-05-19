package view.subviews.menuview;

import controller.menu.Graphic2DMenuController;
import model.game.GameData;
import view.Graphical2DView;
import view.subviews.CustomPanel;
import view.subviews.menuview.viewsections.MenuContentSection;
import view.subviews.menuview.viewsections.MenuLeftSection;
import view.subviews.menuview.viewsections.MenuRightSection;
import view.utils.text.AppText;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import java.awt.Window;
import java.io.File;

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
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Go credits.
     */
    @Override
    public void goCredits() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.goCredits();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Change language.
     */
    @Override
    public void changeLanguage() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.changeLanguage();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Change settings.
     */
    @Override
    public void changeSettings() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.changeSettings();
            this.revalidate();
            this.repaint();
        });
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
            // Create a new file chooser
            JFileChooser jFileChooser = new JFileChooser();

            // set the default directory to the current one
            File workingDirectory = new File(System.getProperty("user.dir"));
            jFileChooser.setCurrentDirectory(workingDirectory);

            // Set a filter to only accept txt files
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    boolean ret = false;

                    int i = file.getAbsolutePath().lastIndexOf('.');
                    String extension = "";
                    if (i >= 0) {
                        extension = file.getAbsolutePath().substring(i + 1);
                    }

                    if (file.isDirectory() || "json".equals(extension)) {
                        ret = true;
                    }
                    return ret;
                }

                @Override
                public String getDescription() {
                    return AppText.getTextFor("menu.loadgame.description");
                }
            });

            String path = null;
            boolean keepAsking = true;
            // Keep asking the user the file to use until he gives it or asks to cancel
            while (path == null && keepAsking) {
                int fileChooserValue = jFileChooser.showDialog(null, AppText.getTextFor("menu.loadgame.title"));
                if (fileChooserValue == JFileChooser.APPROVE_OPTION) {
                    path = jFileChooser.getSelectedFile().getAbsolutePath();
                } else if (fileChooserValue == JFileChooser.CANCEL_OPTION) {
                    keepAsking = false;
                }
            }

            if (path != null) {
                this.controller.playLoadedGame(GameData.load(path));
            }
        });
    }

    /**
     * New game.
     */
    @Override
    public void newGame() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.newGame();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Play offline.
     */
    @Override
    public void playOffline() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.playOffline();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Play online.
     */
    @Override
    public void playOnline() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.playOnline();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Go homepage.
     */
    @Override
    public void goHomepage() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.goHomepage();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Host a game.
     */
    @Override
    public void hostAGame() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.hostAGame();
            this.revalidate();
            this.repaint();
        });
    }

    /**
     * Go lobby.
     */
    @Override
    public void goLobby() {
        SwingUtilities.invokeLater(() -> {
            this.contentSection.goLobby();
            this.revalidate();
            this.repaint();
        });
    }
}
