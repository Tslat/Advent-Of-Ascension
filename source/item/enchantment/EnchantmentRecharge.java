package net.tslat.aoa3.item.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;

public class EnchantmentRecharge extends BaseEnchantment {
	public EnchantmentRecharge() {
		super(Rarity.RARE, EnchantmentsRegister.BLASTER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		setName("aoa3.recharge");
		setRegistryName("aoa3:recharge");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseBlaster;
	}

	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + 7 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 20;
	}
}
