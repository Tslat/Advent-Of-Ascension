package net.nevermine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.nevermine.izer.equipment.Weaponizer;

public class ArchergunTab extends CreativeTabs {
	public ArchergunTab(final int par1, final String par2Str) {
		super(par1, par2Str);
	}

	public Item getTabIconItem() {
		return Weaponizer.ViralArchergun;
	}
}
