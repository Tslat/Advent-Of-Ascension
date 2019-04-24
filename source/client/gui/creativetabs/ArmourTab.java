package net.tslat.aoa3.client.gui.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.ArmourRegister;

public class ArmourTab extends CreativeTabs {
	public ArmourTab(final int id, final String name) {
		super(id, name);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ArmourRegister.AmethindBody, 1, 0);
	}
}
