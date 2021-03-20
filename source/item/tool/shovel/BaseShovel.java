package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import net.tslat.aoa3.common.registration.AoAItemGroups;

import net.minecraft.item.Item.Properties;

public class BaseShovel extends ShovelItem {
	public BaseShovel(IItemTier stats) {
		super(stats, 1.5F, -3.0F, new Properties().durability(stats.getUses()).tab(AoAItemGroups.TOOLS));
	}
}
