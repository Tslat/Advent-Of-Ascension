package net.nevermine.npc.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;

public class RestockedRecipe extends MerchantRecipe {
	public RestockedRecipe(final ItemStack buy, final ItemStack sell) {
		super(buy, sell);
	}

	public RestockedRecipe(final ItemStack buy, final ItemStack buy2, final ItemStack sell) {
		super(buy, buy2, sell);
	}

	public RestockedRecipe(final NBTTagCompound tag) {
		super(tag);
	}

	public boolean isRecipeDisabled() {
		return false;
	}
}
