package net.tslat.aoa3.item.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.item.weapon.cannon.BaseCannon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;

public class EnchantmentBrace extends BaseEnchantment {
	public EnchantmentBrace() {
		super(Rarity.VERY_RARE, EnchantmentsRegister.LIGHT_GUN, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		setName("aoa3.brace");
		setRegistryName("aoa3:brace");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 29;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 80;
	}
}
