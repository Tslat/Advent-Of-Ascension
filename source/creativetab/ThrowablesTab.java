package net.nevermine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.nevermine.izer.equipment.Weaponizer;

public class ThrowablesTab extends CreativeTabs {
	public ThrowablesTab(final int par1, final String par2Str) {
		super(par1, par2Str);
	}

	public Item getTabIconItem() {
		return Weaponizer.Grenade;
	}
}
