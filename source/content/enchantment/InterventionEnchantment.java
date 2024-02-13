package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;

public class InterventionEnchantment extends Enchantment {
	public InterventionEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.UNSTACKABLE, new EquipmentSlot[] {});
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 32;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 100;
	}
}
