package net.nevermine.item.tool.pickaxe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Toolizer;

public class EmberstonePickaxe extends ItemPickaxe {
	public EmberstonePickaxe(final Item.ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);
		setCreativeTab(Toolizer.ToolsTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}
