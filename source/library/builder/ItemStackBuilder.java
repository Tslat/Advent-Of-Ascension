package net.tslat.aoa3.library.builder;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.util.RegistryUtil;

import java.util.concurrent.ThreadLocalRandom;

public final class ItemStackBuilder {
	private final Item item;
	private int count = 1;
	private int damage = 0;
	private CompoundTag nbt = null;

	public ItemStackBuilder(ItemLike item) {
		this.item = item.asItem();
	}

	public ItemStackBuilder(ResourceLocation itemId) {
		this.item = AoARegistries.ITEMS.getEntry(itemId);
	}

	public ItemStackBuilder count(int count) {
		this.count = count;

		return this;
	}

	public ItemStackBuilder damage(int damage) {
		if (!this.item.canBeDepleted())
			throw new IllegalArgumentException("Can't set damage for undamageable item " + RegistryUtil.getId(this.item));

		this.damage = damage;

		return this;
	}

	public ItemStackBuilder withRandomDamage() {
		return damage(ThreadLocalRandom.current().nextInt(0, this.item.getMaxDamage()));
	}

	public ItemStackBuilder usesRemaining(int uses) {
		return damage(this.item.getMaxDamage() - uses);
	}

	public ItemStackBuilder nbt(CompoundTag nbt) {
		this.nbt = nbt;

		return this;
	}

	public ItemStackBuilder nbt(CompoundNBTBuilder nbtBuilder) {
		return nbt(nbtBuilder.build());
	}

	public ItemStack build() {
		ItemStack stack = new ItemStack(this.item, this.count);

		if (this.damage > 0)
			stack.setDamageValue(this.damage);

		if (this.nbt != null) {
			CompoundTag tag = this.nbt;

			if (stack.hasTag()) {
				tag = stack.getTag();

				for (String key : this.nbt.getAllKeys()) {
					tag.put(key, this.nbt.get(key));
				}
			}

			stack.setTag(tag);
		}

		return stack;
	}
}
