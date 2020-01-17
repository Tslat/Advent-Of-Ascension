package net.tslat.aoa3.item.enchantment;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.item.weapon.greatblade.BaseGreatblade;

public class EnchantmentSever extends BaseEnchantment {
	public EnchantmentSever() {
		super(Rarity.UNCOMMON, EnchantmentsRegister.GREATBLADE, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		setName("sever");
		setRegistryName("aoa3:sever");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGreatblade;
	}

	public int getMaxLevel() {
		return 5;
	}

	public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType) {
		return Math.max(0, level * 1.5f);
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 4 + 5 * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 20;
	}
}
