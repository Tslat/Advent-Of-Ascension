package net.nevermine.skill.logging;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.assist.ArmorUtil;

import java.util.Random;

public class loggingHelper {
	private static Random rand = new Random();

	public static int getLootPick(final int lvl) {
		if (lvl < 10)
			return rand.nextInt(2);

		if (lvl < 20)
			return rand.nextInt(3);

		if (lvl < 30)
			return rand.nextInt(4);

		if (lvl < 40)
			return rand.nextInt(5);

		if (lvl < 50)
			return rand.nextInt(6);

		if (lvl < 60)
			return rand.nextInt(7);

		if (lvl < 70)
			return rand.nextInt(8);

		if (lvl < 80)
			return rand.nextInt(9);

		if (lvl < 90)
			return rand.nextInt(10);

		if (lvl < 95)
			return rand.nextInt(11);

		return rand.nextInt(12);
	}

	public static int getLootChance(final int lvl) {
		if (lvl < 20)
			return 20;

		if (lvl < 40)
			return 16;

		if (lvl < 60)
			return 14;

		if (lvl < 80)
			return 11;

		return 8;
	}

	public static boolean isWearingLoggingArmor(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return ArmorUtil.isLoggingArmor(boots, legs, body, helmet);
	}
}
