package net.tslat.aoa3.content.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAEnchantments;
import net.tslat.aoa3.content.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.content.item.weapon.staff.BaseStaff;

public class GreedEnchantment extends Enchantment {
	public GreedEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, AoAEnchantments.AMMO_CONSUMING, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	@Override
	public boolean isCurse() {
		return true;
	}

	@Override
	public boolean isTreasureOnly() {
		return true;
	}

	public boolean canEnchant(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGun || item instanceof BaseBlaster || item instanceof BaseStaff;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 20;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 100;
	}
}
