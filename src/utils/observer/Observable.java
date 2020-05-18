package utils.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * This class should be used as a replacement for the java.util.Observable class which is deprecated.
 * This implementation of the Observer pattern allows more flexibility than the original java one.
 * <br>
 * This class represents an observable object, or "data" in the model-view paradigm.
 * It can be subclassed to represent an object that the application wants to have observed.
 * <br>
 * An observable object can have one or more observers. An observer may be any object that implements interface Observer.
 * <br>
 * The order in which notifications will be delivered is unspecified. The default implementation provided in the Observable class will notify Observers in the order in which they registered interest, but subclasses may change this order, use no guaranteed order, deliver notifications on separate threads, or may guarantee that their subclass follows this order, as they choose.
 * <br>
 * Note that this notification mechanism is has nothing to do with threads and is completely separate from the wait and notify mechanism of class Object.
 * <br>
 * When an observable object is newly created, its set of observers is empty.
 *
 * @param <T> A sub type of Observer, it allows to use methods of this T type.
 */
public abstract class Observable<T extends Observer<T>> {
    /**
     * The list of observers
     */
    private final Collection<T> observers = new ArrayList<>(1);

    /**
     * Notify an observer about the whole state of the app.
     * This method should generally be used to initialize this observer.
     *
     * @param observer the observer
     */
    protected abstract void notifyUpdateEverything(T observer);

    /**
     * Add an observer and initialize it according to the model data.
     *
     * @param observer the observer to add
     */
    public void addObserver(T observer) {
        this.observers.add(observer);
        this.notifyUpdateEverything(observer);
    }

    /**
     * Add a list of observers and initialize them according to the model data.
     *
     * @param observers observers to add
     */
    @SafeVarargs
    public final void addObservers(T... observers) {
        for (T observer : observers) {
            this.addObserver(observer);
        }

        this.notifyUpdateEverything();
    }

    /**
     * Notify all observers about the whole state of the app.
     */
    protected void notifyUpdateEverything() {
        for (T observer : this.observers) {
            this.notifyUpdateEverything(observer);
        }
    }

    /**
     * Iterate through the list of observers. For each of them, call one of its methods.
     *
     * @param consumer the iterator consumer
     */
    protected void forEachObserver(Consumer<T> consumer) {
        this.observers.forEach(consumer);
    }
}
