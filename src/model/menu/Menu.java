package model.menu;

import utils.observer.Observable;
import view.subviews.menuview.MenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu.
 */
public class Menu extends Observable<MenuView> {
    /**
     * The Pages breadcrumb.
     */
    private final List<MenuPage> pagesBreadcrumb = new ArrayList<>();
    /**
     * The Ai mode.
     */
    private boolean aiMode;
    /**
     * The Duo mode.
     */
    private boolean duoMode;
    /**
     * The Online mode.
     */
    private boolean onlineMode;

    /**
     * Instantiates a new Menu.
     */
    public Menu() {
        this.pagesBreadcrumb.add(MenuPage.ROOT);
    }

    /**
     * Add actual page.
     *
     * @param actualPage the actual page
     */
    public void addActualPage(MenuPage actualPage) {
        this.pagesBreadcrumb.add(actualPage);
        this.notifyUpdatePage();
    }

    /**
     * Back previous page.
     */
    public void backPreviousPage() {
        if (this.pagesBreadcrumb.size() <= 1) {
            this.notifyExit();
        } else {
            this.pagesBreadcrumb.remove(this.pagesBreadcrumb.size() - 1);
            this.notifyUpdatePage();
        }
    }

    /**
     * Notify exit.
     */
    protected void notifyExit() {
        this.forEachObserver(MenuView::exit);
    }

    /**
     * Notify update page.
     */
    protected void notifyUpdatePage() {
        this.forEachObserver(menuView -> menuView.updatePage(this.getActualPage()));
    }

    /**
     * Notify update everything.
     *
     * @param observer the observer
     */
    @Override
    protected void notifyUpdateEverything(MenuView observer) {
        observer.updatePage(this.getActualPage());
    }

    /**
     * Gets actual page.
     *
     * @return the actual page
     */
    public MenuPage getActualPage() {
        MenuPage actualPage;

        if (this.pagesBreadcrumb.isEmpty()) {
            actualPage = MenuPage.ROOT;
        } else {
            actualPage = this.pagesBreadcrumb.get(this.pagesBreadcrumb.size() - 1);
        }

        return actualPage;
    }

    /**
     * Is ai mode boolean.
     *
     * @return the boolean
     */
    public boolean isAiMode() {
        return this.aiMode;
    }

    /**
     * Sets ai mode.
     *
     * @param aiMode the ai mode
     */
    public void setAiMode(boolean aiMode) {
        this.aiMode = aiMode;
    }

    /**
     * Is duo mode boolean.
     *
     * @return the boolean
     */
    public boolean isDuoMode() {
        return this.duoMode;
    }

    /**
     * Sets duo mode.
     *
     * @param duoMode the duo mode
     */
    public void setDuoMode(boolean duoMode) {
        this.duoMode = duoMode;
    }

    /**
     * Is online mode boolean.
     *
     * @return the boolean
     */
    public boolean isOnlineMode() {
        return this.onlineMode;
    }

    /**
     * Sets online mode.
     *
     * @param onlineMode the online mode
     */
    public void setOnlineMode(boolean onlineMode) {
        this.onlineMode = onlineMode;
    }
}
