package net.tslat.aoa3.client.gui.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.BlockRegister;

public class DecorationBlocksTab extends CreativeTabs {
	public DecorationBlocksTab(final int id, final String name) {
		super(id, name);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockRegister.bricksBaron, 1, 0);
	}
}
