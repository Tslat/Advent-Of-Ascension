package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;

public class EnchantmentArchmage extends BaseEnchantment {
	public EnchantmentArchmage() {
		super(EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		setName("archmage");
		setRegistryName("aoa3:archmage");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseStaff;
	}

	public int getMaxLevel() {
		return 3;
	}
}
