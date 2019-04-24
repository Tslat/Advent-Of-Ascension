package net.tslat.aoa3.item.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.item.weapon.cannon.BaseCannon;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.sniper.BaseSniper;
import net.tslat.aoa3.library.Enums;

import java.util.UUID;

public class EnchantmentBrace extends BaseEnchantment {
	private static final AttributeModifier mod = new AttributeModifier(UUID.fromString(Enums.AttributeUUIDS.MOVEMENT_SPEED), "AoABraceDebuff", -0.35, 1);

	public EnchantmentBrace() {
		super(EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		setName("brace");
		setRegistryName("aoa3:brace");
	}

	public boolean canApply(ItemStack stack) {
		Item item = stack.getItem();

		return item instanceof BaseGun && !(item instanceof BaseSniper) && !(item instanceof BaseCannon);
	}

	public static AttributeModifier modifier() {
		return mod;
	}
}
