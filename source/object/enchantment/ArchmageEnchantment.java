package net.tslat.aoa3.object.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.object.item.weapon.staff.BaseStaff;

public class ArchmageEnchantment extends Enchantment {
	public ArchmageEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.STAFF, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseStaff;
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 24 + enchantmentLevel * 3;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 50;
	}
}
