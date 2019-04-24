package net.tslat.aoa3.entity.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;

public class AoATraderRecipe extends MerchantRecipe {
	public AoATraderRecipe(NBTTagCompound tagCompound) {
		super(tagCompound);
	}

	public AoATraderRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell) {
		super(buy1, buy2, sell);
	}

	public AoATraderRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell, int toolUsesIn, int maxTradeUsesIn) {
		super(buy1, buy2, sell, toolUsesIn, maxTradeUsesIn);
	}

	public AoATraderRecipe(ItemStack buy1, ItemStack sell) {
		super(buy1, sell);
	}

	public AoATraderRecipe(ItemStack buy1, Item sellItem) {
		super(buy1, sellItem);
	}

	@Override
	public boolean isRecipeDisabled() {
		return false;
	}
}
