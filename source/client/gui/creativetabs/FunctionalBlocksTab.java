package net.tslat.aoa3.client.gui.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.BlockRegister;

public class FunctionalBlocksTab extends CreativeTabs {
	public FunctionalBlocksTab(final int id, final String name) {
		super(id, name);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockRegister.shadowAltar, 1, 0);
	}
}
