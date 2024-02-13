package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

public class RechargeEnchantment extends Enchantment {
	public RechargeEnchantment() {
		super(Rarity.UNCOMMON, AoAEnchantments.BLASTER, ItemUtil.HAND_SLOTS);
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + 6 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}

	public static float modifyCost(ItemStack stack, float baseCost) {
		return baseCost * Math.max(0, 1 - 0.07f * stack.getEnchantmentLevel(AoAEnchantments.RECHARGE.get()));
	}
}
