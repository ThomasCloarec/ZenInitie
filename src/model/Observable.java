package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T> {
    protected List<T> observers = new ArrayList<>();

    public void addObserver(T observer) {
        this.observers.add(observer);
    }
}
