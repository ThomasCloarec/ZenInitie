package model.menu;

import utils.observer.Observable;
import view.subviews.menuview.MenuView;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Observable<MenuView> {
    private final List<MenuPage> pagesBreadcrumb = new ArrayList<>();
    private boolean aiMode;
    private boolean duoMode;
    private boolean onlineMode;

    public Menu() {
        this.pagesBreadcrumb.add(MenuPage.ROOT);
    }

    public void addActualPage(MenuPage actualPage) {
        this.pagesBreadcrumb.add(actualPage);
        this.notifyUpdatePage();
    }

    public void backPreviousPage() {
        this.pagesBreadcrumb.remove(this.pagesBreadcrumb.size() - 1);

        if (this.pagesBreadcrumb.isEmpty()) {
            this.notifyExit();
        } else {
            this.notifyUpdatePage();
        }
    }

    protected void notifyExit() {
        this.forEachObserver(MenuView::exit);
    }

    protected void notifyUpdatePage() {
        this.forEachObserver(menuView -> menuView.updatePage(this.getActualPage()));
    }

    @Override
    protected void notifyUpdateEverything(MenuView observer) {
        observer.updatePage(this.getActualPage());
    }

    @Override
    public void addObserver(MenuView observer) {
        super.addObserver(observer);
        this.notifyUpdatePage();
    }

    public MenuPage getActualPage() {
        return this.pagesBreadcrumb.get(this.pagesBreadcrumb.size() - 1);
    }

    public boolean isAiMode() {
        return this.aiMode;
    }

    public void setAiMode(boolean aiMode) {
        this.aiMode = aiMode;
    }

    public boolean isDuoMode() {
        return this.duoMode;
    }

    public void setDuoMode(boolean duoMode) {
        this.duoMode = duoMode;
    }

    public boolean isOnlineMode() {
        return this.onlineMode;
    }

    public void setOnlineMode(boolean onlineMode) {
        this.onlineMode = onlineMode;
    }
}
