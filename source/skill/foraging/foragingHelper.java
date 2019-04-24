package net.nevermine.skill.foraging;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.assist.ArmorUtil;

import java.util.Random;

public class foragingHelper {
	private static Random rand = new Random();

	public static boolean doForagingLootTest(final int lvl) {
		if (lvl < 20) {
			return rand.nextInt(65) == 0;
		}
		else if (lvl < 40) {
			return rand.nextInt(50) == 0;
		}
		else if (lvl < 60) {
			return rand.nextInt(40) == 0;
		}
		else if (lvl < 80) {
			return rand.nextInt(30) == 0;
		}
		else {
			return rand.nextInt(20) == 0;
		}
	}

	public static int getLootPick(final int lvl) {
		if (lvl < 5)
			return rand.nextInt(2);

		if (lvl < 10)
			return rand.nextInt(3);

		if (lvl < 15)
			return rand.nextInt(4);

		if (lvl < 20)
			return rand.nextInt(5);

		if (lvl < 25)
			return rand.nextInt(6);

		if (lvl < 30)
			return rand.nextInt(7);

		if (lvl < 35)
			return rand.nextInt(8);

		if (lvl < 40)
			return rand.nextInt(9);

		if (lvl < 50)
			return rand.nextInt(10);

		if (lvl < 55)
			return rand.nextInt(11);

		if (lvl < 60)
			return rand.nextInt(12);

		if (lvl < 65)
			return rand.nextInt(13);

		if (lvl < 70)
			return rand.nextInt(14);

		if (lvl < 75)
			return rand.nextInt(15);

		if (lvl < 80)
			return rand.nextInt(16);

		if (lvl < 85)
			return rand.nextInt(17);

		if (lvl < 88)
			return rand.nextInt(18);

		if (lvl < 90)
			return rand.nextInt(19);

		if (lvl < 95)
			return rand.nextInt(20);

		return rand.nextInt(21);
	}

	public static boolean isWearingForagingArmor(EntityPlayer pl) {
		final ItemStack stackBoots = pl.inventory.armorItemInSlot(0);
		final ItemStack stackLegs = pl.inventory.armorItemInSlot(1);
		final ItemStack stackBody = pl.inventory.armorItemInSlot(2);
		final ItemStack stackHelmet = pl.inventory.armorItemInSlot(3);

		final Item boots = stackBoots != null ? stackBoots.getItem() : null;
		final Item legs = stackLegs != null ? stackLegs.getItem() : null;
		final Item body = stackBody != null ? stackBody.getItem() : null;
		final Item helmet = stackHelmet != null ? stackHelmet.getItem() : null;

		return ArmorUtil.isForagingArmor(boots, legs, body, helmet);
	}
}
