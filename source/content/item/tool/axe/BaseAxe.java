package net.tslat.aoa3.content.item.tool.axe;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.library.constant.AttackSpeed;

public class BaseAxe extends AxeItem {
	public BaseAxe(Tier tier) {
		this(tier, 0, AttackSpeed.AXE);
	}

	public BaseAxe(Tier tier, Item.Properties properties) {
		this(tier, 0, AttackSpeed.AXE, properties);
	}

	public BaseAxe(Tier tier, float damageMod, float digSpeedMod) {
		this(tier, damageMod, digSpeedMod, new Properties().durability(tier.getUses()).tab(AoACreativeModeTabs.TOOLS));
	}

	public BaseAxe(Tier tier, float damageMod, float speedMod, Item.Properties properties) {
		super(tier, damageMod, speedMod, properties);
	}
}
