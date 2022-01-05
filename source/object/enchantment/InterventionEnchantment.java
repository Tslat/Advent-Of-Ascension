package net.tslat.aoa3.object.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;

public class InterventionEnchantment extends Enchantment {
	public InterventionEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.UNSTACKABLE, new EquipmentSlotType[] {});
	}

	public boolean canEnchant(ItemStack stack) {
		return stack.getMaxStackSize() == 1;
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
