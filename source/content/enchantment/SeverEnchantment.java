package net.tslat.aoa3.content.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.content.item.weapon.greatblade.BaseGreatblade;

public class SeverEnchantment extends Enchantment {
	public SeverEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, AoAEnchantments.GREATBLADE, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGreatblade;
	}

	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 4 + 5 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}
}
