package net.tslat.aoa3.content.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.content.item.weapon.maul.BaseMaul;

public class CrushEnchantment extends Enchantment {
	public CrushEnchantment() {
		super(Enchantment.Rarity.UNCOMMON, AoAEnchantments.MAUL, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseMaul;
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 10 + 10 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 20;
	}

	@Override
	public void doPostAttack(LivingEntity user, Entity target, int level) {
		super.doPostAttack(user, target, level);
	}
}
