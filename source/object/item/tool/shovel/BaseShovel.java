package net.tslat.aoa3.object.item.tool.shovel;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BaseShovel extends ShovelItem {
	public BaseShovel(IItemTier stats) {
		this(stats, new Properties().durability(stats.getUses()).tab(AoAItemGroups.TOOLS));
	}

	public BaseShovel(IItemTier stats, Item.Properties properties) {
		super(stats, 1.5F, -3.0F, properties);
	}
}
