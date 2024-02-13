package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

public class SeverEnchantment extends Enchantment {
	public SeverEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, AoAEnchantments.GREATBLADE, ItemUtil.MAINHAND_SLOTS);
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 6 + 5 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}

	public static float applyDamageBonus(ItemStack stack, float baseDamage) {
		return baseDamage + stack.getEnchantmentLevel(AoAEnchantments.SEVER.get()) * 1.15f;
	}
}
