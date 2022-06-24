package net.tslat.aoa3.content.item.tool.pickaxe;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.library.constant.AttackSpeed;

public class BasePickaxe extends PickaxeItem {
	public BasePickaxe(Tier tier) {
		this(tier, 0, AttackSpeed.PICKAXE);
	}

	public BasePickaxe(Tier tier, Item.Properties properties) {
		this(tier, 0, AttackSpeed.PICKAXE, properties);
	}

	public BasePickaxe(Tier tier, int damageMod, float digSpeed) {
		this(tier, damageMod, digSpeed, new Item.Properties().durability(tier.getUses()).tab(AoACreativeModeTabs.TOOLS));
	}

	public BasePickaxe(Tier tier, int damageMod, float digSpeed, Item.Properties properties) {
		super(tier, damageMod, digSpeed, properties);
	}
}
