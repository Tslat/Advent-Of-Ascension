package net.tslat.aoa3.library.object;

import javax.annotation.Nonnull;
import java.util.function.Function;

public interface CachedFunction<T, R> extends Function<T, R> {
	static <T, R> CachedFunction<T, R> of(@Nonnull Function<T, R> function) {
		return new Impl<>(function);
	}

	final class Impl<T, R> implements CachedFunction<T, R> {
		private Function<T, R> function;
		private R cached = null;

		private Impl(Function<T, R> function) {
			this.function = function;
		}

		@Override
		public R apply(T t) {
			if (cached != null)
				return cached;

			cached = function.apply(t);
			function = null;

			return cached;
		}
	}
}
