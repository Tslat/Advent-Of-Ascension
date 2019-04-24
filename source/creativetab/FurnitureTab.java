package net.nevermine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.nevermine.izer.SpecialBlockizer;

public class FurnitureTab extends CreativeTabs {
	public FurnitureTab(final int par1, final String par2Str) {
		super(par1, par2Str);
	}

	private Item dropblock() {
		return Item.getItemFromBlock(SpecialBlockizer.GildedSoulBanner);
	}

	public Item getTabIconItem() {
		return dropblock();
	}
}
