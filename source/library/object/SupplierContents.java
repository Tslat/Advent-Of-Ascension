package net.tslat.aoa3.library.object;

import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.Optional;
import java.util.function.Supplier;

public record SupplierContents(Supplier<String> supplier) implements ComponentContents {
	@Override
	public <T> Optional<T> visit(FormattedText.ContentConsumer<T> contentConsumer) {
		return contentConsumer.accept(this.supplier().get());
	}

	@Override
	public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> contentConsumer, Style style) {
		return contentConsumer.accept(style, this.supplier().get());
	}

	@Override
	public String toString() {
		return "supplied{" + this.supplier.get() + "}";
	}

	public static MutableComponent toComponent(Supplier<String> supplier) {
		return MutableComponent.create(new SupplierContents(supplier));
	}
}
