package net.tslat.aoa3.item.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.item.weapon.maul.BaseMaul;

public class EnchantmentCrush extends BaseEnchantment {
	public EnchantmentCrush() {
		super(Rarity.UNCOMMON, EnchantmentsRegister.MAUL, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
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

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 10 + 10 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 20;
	}
}
