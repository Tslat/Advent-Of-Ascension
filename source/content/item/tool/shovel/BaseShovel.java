package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BaseShovel extends ShovelItem {
	public BaseShovel(Tier stats) {
		this(stats, new Properties().durability(stats.getUses()).tab(AoAItemGroups.TOOLS));
	}

	public BaseShovel(Tier stats, Item.Properties properties) {
		super(stats, 1.5F, -3.0F, properties);
	}
}
