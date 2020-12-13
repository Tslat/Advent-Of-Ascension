package net.tslat.aoa3.entity.npc;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.nbt.CompoundNBT;

public class AoATraderRecipe extends MerchantOffer {
	public AoATraderRecipe(CompoundNBT tagCompound) {
		super(tagCompound);
	}

	public AoATraderRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell) {
		this(buy1, buy2, sell, 0, sell.isStackable() ? 5 : 1);
	}

	public AoATraderRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell, int defaultUses, int maxUses) {
		super(buy1, buy2, sell, defaultUses, maxUses, 0, 0, 0);
	}

	public AoATraderRecipe(ItemStack buy1, ItemStack sell) {
		this(buy1, ItemStack.EMPTY, sell);
	}

	public AoATraderRecipe(ItemStack buy1, Item sellItem) {
		this(buy1, ItemStack.EMPTY, new ItemStack(sellItem));
	}
}
