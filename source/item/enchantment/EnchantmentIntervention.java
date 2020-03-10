package net.tslat.aoa3.item.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;

public class EnchantmentIntervention extends BaseEnchantment {
	public EnchantmentIntervention() {
		super(Rarity.VERY_RARE, EnchantmentsRegister.UNSTACKABLE, new EntityEquipmentSlot[] {});
		setName("aoa3.intervention");
		setRegistryName("aoa3:intervention");
	}

	public boolean canApply(ItemStack stack) {
		return stack.getMaxStackSize() == 1;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 32;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 100;
	}
}
