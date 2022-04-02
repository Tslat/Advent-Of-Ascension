package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.content.item.weapon.blaster.BaseBlaster;

public class RechargeEnchantment extends Enchantment {
	public RechargeEnchantment() {
		super(Enchantment.Rarity.RARE, AoAEnchantments.BLASTER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
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
