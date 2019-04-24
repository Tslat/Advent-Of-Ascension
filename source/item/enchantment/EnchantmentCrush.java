package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.item.weapon.maul.BaseMaul;

public class EnchantmentCrush extends BaseEnchantment {
	public EnchantmentCrush() {
		super(EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		setName("crush");
		setRegistryName("aoa3:crush");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseMaul;
	}

	public int getMaxLevel() {
		return 3;
	}
}
