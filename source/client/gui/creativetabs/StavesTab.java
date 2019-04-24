package net.tslat.aoa3.client.gui.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.WeaponRegister;

public class StavesTab extends CreativeTabs {
	public StavesTab(final int id, final String name) {
		super(id, name);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(WeaponRegister.staffCelestial, 1, 0);
	}
}
