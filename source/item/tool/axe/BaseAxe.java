package net.tslat.aoa3.item.tool.axe;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BaseAxe extends AxeItem {
	public BaseAxe(IItemTier stats) {
		this(stats, new Properties().durability(stats.getUses()).tab(AoAItemGroups.TOOLS));
	}

	public BaseAxe(IItemTier stats, Item.Properties properties) {
		super(stats, 1.5F, -2.8F, properties);
	}
}
