package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

public class GreedEnchantment extends Enchantment {
	public GreedEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.AMMO_CONSUMING, ItemUtil.HAND_SLOTS);
	}

	@Override
	public boolean isCurse() {
		return true;
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 20;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 100;
	}
}
