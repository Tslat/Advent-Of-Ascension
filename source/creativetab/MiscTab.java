package net.nevermine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.nevermine.izer.Itemizer;

public class MiscTab extends CreativeTabs {
	public MiscTab(final int par1, final String par2Str) {
		super(par1, par2Str);
	}

	public Item getTabIconItem() {
		return Itemizer.AmethystIngot;
	}
}
