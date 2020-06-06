package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public abstract class BaseEnchantment extends Enchantment {
	protected BaseEnchantment(Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] slots) {
		super(rarity, type, slots);
	}

	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}
}
