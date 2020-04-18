package model.menu;

import model.Observable;
import view.sub_views.menu_view.MenuView;

public abstract class MenuObservable extends Observable<MenuView> {
    protected void notifyExit() {
        this.observers.forEach(MenuView::exit);
    }

    protected void notifyUpdatePage(Menu menu) {
        this.observers.forEach(menuView -> menuView.updatePage(menu));
    }
}
