package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BasePickaxe extends PickaxeItem {
	public BasePickaxe(Tier stats) {
		this(stats, new Item.Properties().durability(stats.getUses()).tab(AoAItemGroups.TOOLS));
	}

	public BasePickaxe(Tier stats, Item.Properties properties) {
		super(stats, 1, -2.8F, properties);
	}
}
