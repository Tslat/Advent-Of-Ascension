package net.tslat.aoa3.item.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;

public class EnchantmentGreed extends BaseEnchantment {
	public EnchantmentGreed() {
		super(Rarity.VERY_RARE, EnchantmentsRegister.AMMO_CONSUMING, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		setName("aoa3.greed");
		setRegistryName("aoa3:greed");
	}

	@Override
	public boolean isCurse() {
		return true;
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGun || item instanceof BaseBlaster || item instanceof BaseStaff;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 20;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 100;
	}
}
