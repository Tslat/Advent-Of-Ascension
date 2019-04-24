package net.nevermine.item.tool.shovel;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Toolizer;

public class RositeShovel extends ItemSpade {
	public RositeShovel(final Item.ToolMaterial p_i45353_1_) {
		super(p_i45353_1_);
		setCreativeTab(Toolizer.ToolsTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
