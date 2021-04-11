package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BasePickaxe extends PickaxeItem {
	public BasePickaxe(IItemTier stats) {
		this(stats, new Item.Properties().durability(stats.getUses()).tab(AoAItemGroups.TOOLS));
	}

	public BasePickaxe(IItemTier stats, Item.Properties properties) {
		super(stats, 1, -2.8F, properties);
	}
}
