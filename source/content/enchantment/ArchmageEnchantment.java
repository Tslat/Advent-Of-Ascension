package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

public class ArchmageEnchantment extends Enchantment {
	public ArchmageEnchantment() {
		super(Rarity.RARE, AoAEnchantments.STAFF, ItemUtil.HAND_SLOTS);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 17 + (enchantmentLevel - 1) * 6;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 50;
	}
}
