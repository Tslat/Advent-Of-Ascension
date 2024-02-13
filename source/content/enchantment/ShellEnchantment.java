package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

public class ShellEnchantment extends Enchantment {
	public ShellEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, AoAEnchantments.BULLET_FIRING, ItemUtil.HAND_SLOTS);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 5 + 10 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}

	public static float applyDamageBonus(ItemStack stack, float baseDamage) {
		return baseDamage * (1 + (stack.getEnchantmentLevel(AoAEnchantments.SHELL.get()) * 0.1f));
	}
}
