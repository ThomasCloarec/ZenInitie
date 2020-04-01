package model.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Menu extends Observable {
    private final List<Page> pagesBreadcrumb = new ArrayList<>();
    private boolean aiMode;
    private boolean duoMode;
    private boolean onlineMode;

    public Menu() {
        this.pagesBreadcrumb.add(Page.ROOT);
    }

    public void addActualPage(Page actualPage) {
        this.pagesBreadcrumb.add(actualPage);
        this.setChanged();
        this.notifyObservers(UpdateNotification.UPDATE_PAGE);
    }

    public void backPreviousPage() {
        if (this.pagesBreadcrumb.size() > 1) {
            this.pagesBreadcrumb.remove(this.pagesBreadcrumb.size() - 1);
            this.setChanged();
            this.notifyObservers(UpdateNotification.UPDATE_PAGE);
        }
    }

    public void exit() {
        this.setChanged();
        this.notifyObservers(UpdateNotification.EXIT);
    }

    public void setOnlineMode(boolean onlineMode) {
        this.onlineMode = onlineMode;
    }

    public Page getActualPage() {
        return this.getPagesBreadcrumb().get(this.getPagesBreadcrumb().size() - 1);
    }

    public List<Page> getPagesBreadcrumb() {
        return this.pagesBreadcrumb;
    }

    public enum Page {
        ROOT,
        PLAY_ONLINE,
        LOAD_GAME,
        NEW_GAME,
        PLAY_OFFLINE,
        CHANGE_SETTINGS,
        CHANGE_LANGUAGE
    }

    public enum UpdateNotification {
        UPDATE_PAGE,
        EXIT
    }
}
