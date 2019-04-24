package net.nevermine.gui.pouch;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.common.nevermine;

import java.util.List;

public class ItemRunePouch extends Item {
	public ItemRunePouch() {
		setUnlocalizedName("runePouch");
		setMaxStackSize(1);
	}

	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		if (!world.isRemote) {
			player.openGui(nevermine.instance, 500, world, 0, 0, 0);
		}
		return stack;
	}

	public static ItemStack getStackInSlot(final ItemStack stack, final int slot) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Inventory")) {
			final NBTTagList l = stack.getTagCompound().getTagList("Inventory", 10);
			NBTTagCompound c = new NBTTagCompound();
			for (int n = 0; n < 18; ++n) {
				if ((l.getCompoundTagAt(n).getByte("Slot") & 0xFF) == slot) {
					c = l.getCompoundTagAt(n);
				}
			}
			return ItemStack.loadItemStackFromNBT(c);
		}
		return null;
	}

	public static void setStackInSlot(final ItemStack stack, final int slot, final ItemStack toSet) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Inventory")) {
			final NBTTagList l = stack.getTagCompound().getTagList("Inventory", 10);
			NBTTagCompound c = new NBTTagCompound();
			if (toSet != null) {
				c = toSet.writeToNBT(c);
			}
			c.setByte("Slot", (byte)slot);
			l.func_150304_a(slot, c);
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getLocaleString("item.RunePouch.desc.1"));
	}

	public static void decrStackSize(final ItemStack stack, final int slot, final int amount) {
		final ItemStack old = getStackInSlot(stack, slot);
		if (old != null) {
			final ItemStack itemStack = old;
			itemStack.stackSize -= amount;
		}
		setStackInSlot(stack, slot, old);
	}
}
