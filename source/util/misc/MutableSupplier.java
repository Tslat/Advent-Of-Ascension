package net.tslat.aoa3.util.misc;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class MutableSupplier<T> implements Supplier<T> {
	private Supplier<T> supplier;

	public MutableSupplier (@Nullable Supplier<T> supplier) {
		this.supplier = supplier;
	}

	public void update(@Nullable Supplier<T> supplier) {
		this.supplier = supplier;
	}

	@Nullable
	@Override
	public T get() {
		return supplier.get();
	}
}