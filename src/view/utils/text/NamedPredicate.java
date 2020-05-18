package view.utils.text;

import java.util.function.Predicate;

/**
 * The type Named predicate.
 *
 * @param <T> the type parameter
 */
public class NamedPredicate<T> implements Predicate<T> {
    /**
     * The Name.
     */
    private final String name;
    /**
     * The Predicate.
     */
    private final Predicate<? super T> predicate;

    /**
     * Instantiates a new Named predicate.
     *
     * @param name      the name
     * @param predicate the predicate
     */
    public NamedPredicate(String name, Predicate<? super T> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    /**
     * Test boolean.
     *
     * @param t the t
     * @return the boolean
     */
    @Override
    public boolean test(T t) {
        return this.predicate.test(t);
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return this.name;
    }
}
