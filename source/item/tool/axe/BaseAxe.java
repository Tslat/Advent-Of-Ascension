package net.tslat.aoa3.item.tool.axe;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BaseAxe extends AxeItem {
	public BaseAxe(IItemTier stats) {
		super(stats, 1.5F, -2.8F, new Properties().maxDamage(stats.getMaxUses()).group(AoAItemGroups.TOOLS));
	}
}
