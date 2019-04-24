package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;

public class EnchantmentRecharge extends BaseEnchantment {
	public EnchantmentRecharge() {
		super(EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		setName("recharge");
		setRegistryName("aoa3:recharge");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseBlaster;
	}

	public int getMaxLevel() {
		return 5;
	}
}
