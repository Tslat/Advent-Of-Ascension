package net.tslat.aoa3.item.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.item.weapon.shotgun.BaseShotgun;

public class EnchantmentForm extends BaseEnchantment {
	public EnchantmentForm() {
		super(Rarity.RARE, EnchantmentsRegister.SHOTGUN, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		setName("form");
		setRegistryName("aoa3:form");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseShotgun;
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 5 + enchantmentLevel * 9;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 20;
	}
}
