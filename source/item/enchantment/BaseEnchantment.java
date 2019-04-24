package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public abstract class BaseEnchantment extends Enchantment {
	protected BaseEnchantment(EnumEnchantmentType type, EntityEquipmentSlot[] slots) {
		super(Rarity.VERY_RARE, type, slots);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isAllowedOnBooks() {
		return false;
	}
}
