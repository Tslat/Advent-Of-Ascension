package net.tslat.aoa3.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

public class ControlEnchantment extends Enchantment {
	public ControlEnchantment() {
		super(Enchantment.Rarity.RARE, AoAEnchantments.GUN, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGun;
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 15 + enchantmentLevel * 5;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}
}
