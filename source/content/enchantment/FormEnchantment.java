package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

public class FormEnchantment extends Enchantment {
	public FormEnchantment() {
		super(Rarity.UNCOMMON, AoAEnchantments.SHOTGUN, ItemUtil.HAND_SLOTS);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 9 + (enchantmentLevel - 1) * 9;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}

	public static float modifySpread(ItemStack stack, float initialSpread) {
		return initialSpread * (1 - 0.15f * stack.getEnchantmentLevel(AoAEnchantments.FORM.get()));
	}
}
