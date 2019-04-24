package net.nevermine.skill.creation;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.assist.ArmorUtil;

public class creationSkillHelper {
	public static boolean isWearingArmour(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return ArmorUtil.isCreationArmor(boots, legs, body, helmet);
	}
}
