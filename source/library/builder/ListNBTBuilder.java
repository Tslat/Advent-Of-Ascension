package net.tslat.aoa3.library.builder;

import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public final class ListNBTBuilder {
	private final ListTag tag;

	public ListNBTBuilder() {
		this(new ListTag());
	}

	public ListNBTBuilder(ListTag tag) {
		this.tag = tag;
	}

	public ListNBTBuilder with(Tag... tags) {
		for (Tag tag : tags) {
			this.tag.add(tag);
		}

		return this;
	}

	public ListNBTBuilder with(ListNBTBuilder builder) {
		return with(builder.build());
	}

	public ListNBTBuilder with(CompoundNBTBuilder builder) {
		return with(builder.build());
	}

	public ListTag build() {
		return this.tag;
	}
}
