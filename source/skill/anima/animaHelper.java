package net.nevermine.skill.anima;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.assist.ArmorUtil;

public class animaHelper {
	public static int getExpDenominator(final int lvl) {
		if (lvl <= 4)
			return 4;

		if (lvl <= 14)
			return 8;

		if (lvl <= 29)
			return 30;

		if (lvl <= 44)
			return 60;

		if (lvl <= 59)
			return 85;

		if (lvl <= 74)
			return 112;

		if (lvl <= 89)
			return 180;

		if (lvl <= 94)
			return 220;

		return 280;
	}

	public static double getCostModifier(EntityPlayer pl) {
		double mod = 1.0;
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		if (ArmorUtil.isAnimaArmor(boots, legs, body, helmet))
			mod = 0.5;

		return mod;
	}
}
