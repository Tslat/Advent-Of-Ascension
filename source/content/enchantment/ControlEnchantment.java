package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

public class ControlEnchantment extends Enchantment {
	public ControlEnchantment() {
		super(Rarity.COMMON, AoAEnchantments.GUN, ItemUtil.HAND_SLOTS);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 15 + enchantmentLevel * 5;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 30;
	}

	public static float modifyRecoil(ItemStack stack, float baseRecoil) {
		return baseRecoil * (1 - 0.15f * stack.getEnchantmentLevel(AoAEnchantments.CONTROL.get()));
	}
}
