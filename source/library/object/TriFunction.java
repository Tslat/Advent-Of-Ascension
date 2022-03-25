package net.tslat.aoa3.library.object;

/**
 * Represents a function that accepts three arguments and produces a result. This is the triple-argument equivalent of {@code Function}
 *
 * This is a functional interface whose functional method is apply(Object, Object, Object).
 */
@FunctionalInterface
public interface TriFunction<X, Y, Z, R> {
	/**
	 * Applies this function to the given arguments.
	 *
	 * @param x - the first function argument
	 * @param y - the second function argument
	 * @param z - the third function argument
	 * @return the function result
	 */
	R apply(X x, Y y, Z z);
}
