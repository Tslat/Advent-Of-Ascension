package net.nevermine.item.weapon.staff;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.nevermine.assist.ItemUtil;
import net.nevermine.event.player.Ticker;
import net.nevermine.item.ItemRune;

import java.util.HashMap;

public abstract class BaseStaff extends Item {
	private HashMap<ItemRune, Integer> runes;

	public BaseStaff(HashMap<ItemRune, Integer> rns) {
		runes = rns;
		setFull3D();
		maxStackSize = 1;
	}

	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}

		if (player.capabilities.isCreativeMode || Ticker.tick >= stack.getTagCompound().getLong("lastShotTime")) {
			if (!player.worldObj.isRemote) {
				if (checkConditions(world, stack, player)) {
					if (ItemUtil.tryConsumeRunes(runes, player, true, stack)) {
						fireStaff(world, stack, player);
					}
				}

				stack.getTagCompound().setLong("lastShotTime", Ticker.tick + 5);
			}
		}

		if (stack.getTagCompound().getLong("lastShotTime") >= 100000L || stack.getTagCompound().getLong("lastShotTime") > Ticker.tick + 20) {
			stack.getTagCompound().setLong("lastShotTime", 0L);
		}

		return stack;
	}

	public abstract void fireStaff(final World p0, final ItemStack p1, final EntityPlayer p2);

	public boolean checkConditions(final World world, final ItemStack stack, final EntityPlayer player) {
		return true;
	}
}
