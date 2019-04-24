package net.nevermine.skill.vulcanism;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.assist.ArmorUtil;

public class vulcanismHelper {
	public static float getVulcaneDamageMultiplier(final int lvl) {
		if (lvl < 10)
			return 1.0f;

		if (lvl < 20)
			return 1.1f;

		if (lvl < 30)
			return 1.2f;

		if (lvl < 40)
			return 1.3f;

		if (lvl < 50)
			return 1.4f;

		if (lvl < 60)
			return 1.6f;

		if (lvl < 70)
			return 1.8f;

		if (lvl < 80)
			return 2.0f;

		if (lvl < 90)
			return 2.15f;

		if (lvl < 96)
			return 2.3f;

		return 2.5f;
	}

	public static int getExpDenominator(final int lvl) {
		if (lvl < 5)
			return 6;

		if (lvl < 15)
			return 14;

		if (lvl < 30)
			return 30;

		if (lvl < 45)
			return 60;

		if (lvl < 60)
			return 100;

		if (lvl < 75)
			return 160;

		if (lvl < 90)
			return 250;

		if (lvl < 95)
			return 310;

		return 400;
	}

	public static boolean isWearingVulcanismArmor(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return ArmorUtil.isVulcanismArmor(boots, legs, body, helmet);
	}
}
