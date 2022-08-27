package net.tslat.aoa3.library.builder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public final class CompoundNBTBuilder {
	private final CompoundTag tag;

	public CompoundNBTBuilder() {
		this(new CompoundTag());
	}

	public CompoundNBTBuilder(CompoundTag tag) {
		this.tag = tag;
	}

	public CompoundNBTBuilder with(String key, Tag tag) {
		this.tag.put(key, tag);

		return this;
	}

	public CompoundNBTBuilder with(String key, CompoundNBTBuilder builder) {
		return with(key, builder.build());
	}

	public CompoundNBTBuilder with(String key, ListNBTBuilder builder) {
		return with(key, builder.build());
	}

	public CompoundTag build() {
		return this.tag;
	}
}
