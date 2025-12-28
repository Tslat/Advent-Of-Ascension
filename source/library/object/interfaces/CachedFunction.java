package net.tslat.aoa3.library.object.interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A memoizing {@link Function} with generics.
 * <p>
 * This differs from typical memoizing functions in that it only caches the first result, allowing for it to be used as
 * a lightweight lazily-initialized supplier that takes an input argument.<br>
 * Additionally, the function itself is dropped after caching the result, to alleviate memory leaks and other similar
 * potential effects.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface CachedFunction<T, R> extends Function<T, R> {
	/**
	 * Create a new CachedFunction for the given input function
	 */
	static <T, R> CachedFunction<T, R> of(@NotNull Function<T, R> inputFunction) {
		return new CachedFunction<>() {
			private Function<T, R> function = inputFunction;
			private R cached = null;

			@Override
			public R apply(T t) {
				if (this.cached != null)
					return this.cached;

				this.cached = this.function.apply(t);
				this.function = null;

				return this.cached;
			}
		};
	}
}
