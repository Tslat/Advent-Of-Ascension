package net.tslat.aoa3.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.item.weapon.cannon.BaseCannon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;

import java.util.UUID;

public class BraceEnchantment extends Enchantment {
	public static final AttributeModifier BRACE_DEBUFF = new AttributeModifier(UUID.fromString("a1371c64-c09e-4ed6-adfd-5afbaea79369"), "AoABraceDebuff", -0.35, AttributeModifier.Operation.MULTIPLY_BASE);

	public BraceEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.LIGHT_GUN, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon);
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
