package net.nevermine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.nevermine.izer.Blockizer;

public class LightingTab extends CreativeTabs {
	public LightingTab(final int par1, final String par2Str) {
		super(par1, par2Str);
	}

	private Item dropblock() {
		return Item.getItemFromBlock(Blockizer.LampAquatic);
	}

	public Item getTabIconItem() {
		return dropblock();
	}
}
