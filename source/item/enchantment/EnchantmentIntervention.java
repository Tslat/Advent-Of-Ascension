package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentIntervention extends BaseEnchantment {
	public EnchantmentIntervention() {
		super(EnumEnchantmentType.ALL, new EntityEquipmentSlot[] {});
		setName("intervention");
		setRegistryName("aoa3:intervention");
	}

	public boolean canApply(ItemStack stack) {
		return stack.getMaxStackSize() == 1;
	}
}
