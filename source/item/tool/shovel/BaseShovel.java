package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ShovelItem;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BaseShovel extends ShovelItem {
	public BaseShovel(IItemTier stats) {
		super(stats, 1.5F, -3.0F, new Properties().maxDamage(stats.getMaxUses()).group(AoAItemGroups.TOOLS));
	}
}
