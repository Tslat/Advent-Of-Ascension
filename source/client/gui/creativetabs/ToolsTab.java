package net.tslat.aoa3.client.gui.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.ToolRegister;

public class ToolsTab extends CreativeTabs {
	public ToolsTab(final int id, final String name) {
		super(id, name);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ToolRegister.pickaxeLimonite, 1, 0);
	}
}
