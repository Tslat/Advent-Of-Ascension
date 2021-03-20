package net.tslat.aoa3.item.tool.axe;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.tslat.aoa3.common.registration.AoAItemGroups;

import net.minecraft.item.Item.Properties;

public class BaseAxe extends AxeItem {
	public BaseAxe(IItemTier stats) {
		super(stats, 1.5F, -2.8F, new Properties().durability(stats.getUses()).tab(AoAItemGroups.TOOLS));
	}
}
