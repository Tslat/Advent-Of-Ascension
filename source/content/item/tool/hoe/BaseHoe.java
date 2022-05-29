package net.tslat.aoa3.content.item.tool.hoe;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.tslat.aoa3.common.registration.AoAItemGroups;

public class BaseHoe extends HoeItem {
	public BaseHoe(Tier tier, int damageMod, float speedMod) {
		this(tier, damageMod, speedMod, new Properties().durability(tier.getUses()).tab(AoAItemGroups.TOOLS));
	}

	public BaseHoe(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
		super(tier, attackDamageModifier, attackSpeedModifier, properties);
	}
}
