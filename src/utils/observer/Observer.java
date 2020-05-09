package utils.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * This class should be used as a replacement for the java.util.Observer interface which is deprecated.
 * This implementation of the Observer pattern allows more flexibility than the original java one.
 * <br>
 * A class can implement the Observer interface when it wants to be informed of changes in observable objects.
 *
 * @param <T> A sub type of Observer, it allows to use methods of this T type.
 */
public interface Observer<T extends Observer<T>> {
    /**
     * This method allow the user to iterate through all the observer children and to use them in some way (generally to call an update method).
     *
     * @param consumer the functional interface iterating over all the observer children.
     */
    default void forEachObserverChild(Consumer<T> consumer) {
        this.getObserverChildren().forEach(consumer);
    }

    /**
     * Allow the use of method reference for single integer parameter observer methods.
     *
     * @param consumer The method of the observer
     * @param arg2     The integer parameter of the method
     */
    default void forEachObserverChild(BiConsumer<T, Integer> consumer, Integer arg2) {
        this.forEachObserverChild(arg1 -> consumer.accept(arg1, arg2));
    }

    /**
     * Allow the use of method reference for single boolean parameter observer methods.
     *
     * @param consumer The method of the observer
     * @param arg2     The boolean parameter of the method
     */
    default void forEachObserverChild(BiConsumer<T, Boolean> consumer, Boolean arg2) {
        this.forEachObserverChild(arg1 -> consumer.accept(arg1, arg2));
    }

    /**
     * Allow the use of method reference for single String parameter observer methods.
     *
     * @param consumer The method of the observer
     * @param arg2     The String parameter of the method
     */
    default void forEachObserverChild(BiConsumer<T, String> consumer, String arg2) {
        this.forEachObserverChild(arg1 -> consumer.accept(arg1, arg2));
    }

    /**
     * The implementer may override this method to add observer children.
     * When the parent observer see one of its update method called, its children will see theirs too (even though they are not known observers of the observable).
     * In other words, we could say that observer children are observing the parent observer.
     * <br>
     * By default it is an empty list in case the observer has no observer children
     *
     * @return the list of observer children given by the implementer.
     */
    default List<T> getObserverChildren() {
        return new ArrayList<>(0);
    }
}