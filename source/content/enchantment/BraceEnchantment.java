package net.tslat.aoa3.content.enchantment;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.tslat.aoa3.common.registration.item.AoAEnchantments;
import net.tslat.aoa3.util.ItemUtil;

import java.util.UUID;

public class BraceEnchantment extends Enchantment {
	public static final AttributeModifier BRACE_DEBUFF = new AttributeModifier(UUID.fromString("a1371c64-c09e-4ed6-adfd-5afbaea79369"), "AoABraceDebuff", -0.35, AttributeModifier.Operation.MULTIPLY_BASE);

	public BraceEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.LIGHT_GUN, ItemUtil.HAND_SLOTS);
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 29;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 80;
	}
}
