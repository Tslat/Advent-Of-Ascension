package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;

public class EnchantmentGreed extends BaseEnchantment {
	public EnchantmentGreed() {
		super(EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		setName("greed");
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
}
