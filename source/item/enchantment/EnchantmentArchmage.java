package net.tslat.aoa3.item.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;

public class EnchantmentArchmage extends BaseEnchantment {
	public EnchantmentArchmage() {
		super(Rarity.VERY_RARE, EnchantmentsRegister.STAFF, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
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

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 24 + enchantmentLevel * 3;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 50;
	}
}
