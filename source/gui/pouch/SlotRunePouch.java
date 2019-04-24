package net.nevermine.gui.pouch;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.nevermine.item.ItemRune;

public class SlotRunePouch extends Slot {
	public SlotRunePouch(final IInventory p_i1824_1_, final int p_i1824_2_, final int p_i1824_3_, final int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}

	public boolean isItemValid(final ItemStack stack) {
		return stack.getItem() instanceof ItemRune;
	}
}
