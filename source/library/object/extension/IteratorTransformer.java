package net.tslat.aoa3.library.object.extension;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Function;

/**
 *
 * @param <T>
 * @param <O>
 */
public final class IteratorTransformer<T, O> implements Iterable<O> {
    private final Iterable<T> wrapped;
    private final Function<T, O> transformer;

    public IteratorTransformer(Iterable<T> wrapped, Function<T, O> transformer) {
        this.wrapped = wrapped;
        this.transformer = transformer;
    }

    @NotNull
    @Override
    public Iterator<O> iterator() {
        final Iterator<T> iterator = this.wrapped.iterator();
        final Function<T, O> transformer = this.transformer;

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public O next() {
                T next = iterator.next();

                return transformer.apply(next);
            }

            @Override
            public void remove() {
                iterator.remove();
            }
        };
    }
}
