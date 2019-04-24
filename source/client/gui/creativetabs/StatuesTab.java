package net.tslat.aoa3.client.gui.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.BlockRegister;

public class StatuesTab extends CreativeTabs {
	public StatuesTab(final int id, final String name) {
		super(id, name);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockRegister.statueSmash, 1, 0);
}
}
