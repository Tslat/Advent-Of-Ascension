package net.tslat.aoa3.library.object;

import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class MutableSupplier<T> implements Supplier<T> {
	@Nullable
	private Supplier<T> supplier;

	public MutableSupplier() {
		this(null);
	}

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