package model.menu;

import view.sub_views.menu_view.MenuView;

import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuObservable {
    private final List<Page> pagesBreadcrumb = new ArrayList<>();
    private boolean aiMode;
    private boolean duoMode;
    private boolean onlineMode;

    public Menu() {
        this.pagesBreadcrumb.add(Page.ROOT);
    }

    public void addActualPage(Page actualPage) {
        this.pagesBreadcrumb.add(actualPage);
        this.notifyUpdatePage(this);
    }

    public void backPreviousPage() {
        this.pagesBreadcrumb.remove(this.pagesBreadcrumb.size() - 1);

        if (!this.pagesBreadcrumb.isEmpty()) {
            this.notifyUpdatePage(this);
        } else {
            this.notifyExit();
        }
    }

    @Override
    public void addObserver(MenuView observer) {
        super.addObserver(observer);
        this.notifyUpdatePage(this);
    }

    public Page getActualPage() {
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

    public enum Page {
        ROOT,
        PLAY_ONLINE,
        LOAD_GAME,
        NEW_GAME,
        PLAY_OFFLINE,
        CHANGE_SETTINGS,
        CHANGE_LANGUAGE,
        CREDITS
    }
}
