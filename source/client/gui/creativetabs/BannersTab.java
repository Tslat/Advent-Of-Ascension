package net.tslat.aoa3.client.gui.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.BlockRegister;

public class BannersTab extends CreativeTabs {
	public BannersTab(final int id, final String name) {
		super(id, name);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockRegister.bannerAncient, 1, 0);
}
}
