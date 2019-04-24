package net.nevermine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.nevermine.izer.equipment.Slabizer;

public class SlabTab extends CreativeTabs {
	public SlabTab(final int par1, final String par2Str) {
		super(par1, par2Str);
	}

	public Item getTabIconItem() {
		return Slabizer.EnderCarrierSlab;
	}
}
