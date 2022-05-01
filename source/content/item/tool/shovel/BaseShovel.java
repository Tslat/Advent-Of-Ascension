package net.tslat.aoa3.content.item.tool.shovel;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.library.constant.AttackSpeed;

public class BaseShovel extends ShovelItem {
	public BaseShovel(Tier tier) {
		this(tier, 0, AttackSpeed.SHOVEL);
	}

	public BaseShovel(Tier tier, Item.Properties properties) {
		this(tier, 0, AttackSpeed.SHOVEL, properties);
	}

	public BaseShovel(Tier tier, float damageMod, float digSpeed) {
		this(tier, damageMod, digSpeed, new Properties().durability(tier.getUses()).tab(AoAItemGroups.TOOLS));
	}

	public BaseShovel(Tier tier, float damageMod, float speedMod, Item.Properties properties) {
		super(tier, damageMod, speedMod, properties);
	}
}
