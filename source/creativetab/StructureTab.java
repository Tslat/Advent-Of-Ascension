package net.nevermine.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.nevermine.izer.Blockizer;

public class StructureTab extends CreativeTabs {
	public StructureTab(final int par1, final String par2Str) {
		super(par1, par2Str);
	}

	private Item dropblock() {
		return Item.getItemFromBlock(Blockizer.LunarCreationTable);
	}

	public Item getTabIconItem() {
		return dropblock();
	}
}
