package net.tslat.aoa3.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class FireworkUtil {
	public static ItemStack getExplosiveExpertFireworks() {
		ItemStack stack = new ItemStack(Items.FIREWORKS);
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagCompound fireworksTag = new NBTTagCompound();

		fireworksTag.setByte("Flight", (byte)42);
		tag.setTag("Fireworks", fireworksTag);
		tag.setBoolean("AoA", true);
		stack.setTagCompound(tag);

		return stack;
	}
}
