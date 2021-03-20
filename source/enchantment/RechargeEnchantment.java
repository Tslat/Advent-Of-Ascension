package net.tslat.aoa3.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;

public class RechargeEnchantment extends Enchantment {
	public RechargeEnchantment() {
		super(Enchantment.Rarity.RARE, AoAEnchantments.BLASTER, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseBlaster;
	}

	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + 7 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}
}
