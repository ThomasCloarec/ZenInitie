package model.menu;

import model.Observable;
import view.sub_views.menu_view.MenuView;

public abstract class MenuObservable extends Observable<MenuView> {
    @Override
    public void addObserver(MenuView observer) {
        super.addObserver(observer);
        observer.start();
    }

    protected void notifyExit() {
        this.observers.forEach(MenuView::exit);
    }

    protected void notifyUpdatePage(Menu menu) {
        this.observers.forEach(menuView -> menuView.updatePage(menu));
    }
}
