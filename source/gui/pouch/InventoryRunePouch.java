package net.nevermine.gui.pouch;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryRunePouch implements IInventory {
	public ItemStack[] slots;

	public InventoryRunePouch(final ItemStack stack) {
		slots = new ItemStack[18];
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Inventory")) {
			final NBTTagList l = stack.getTagCompound().getTagList("Inventory", 10);
			for (int i = 0; i < l.tagCount(); ++i) {
				final NBTTagCompound compound = l.getCompoundTagAt(i);
				final int j = compound.getByte("Slot") & 0xFF;
				if (j >= 0 && j < slots.length) {
					slots[j] = ItemStack.loadItemStackFromNBT(compound);
				}
			}
		}
	}

	public int getSizeInventory() {
		return slots.length;
	}

	public ItemStack getStackInSlot(final int slot) {
		return slots[slot];
	}

	public ItemStack decrStackSize(final int slot, final int count) {
		if (slots[slot] == null) {
			return null;
		}
		if (slots[slot].stackSize <= count) {
			final ItemStack itemstack = slots[slot];
			slots[slot] = null;
			return itemstack;
		}
		final ItemStack itemstack = slots[slot].splitStack(count);
		if (slots[slot].stackSize == 0) {
			slots[slot] = null;
		}
		return itemstack;
	}

	public ItemStack getStackInSlotOnClosing(final int slot) {
		if (slots[slot] != null) {
			final ItemStack itemstack = slots[slot];
			slots[slot] = null;
			return itemstack;
		}
		return null;
	}

	public void setInventorySlotContents(final int slot, final ItemStack stack) {
		slots[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	public String getInventoryName() {
		return "Rune Pouch";
	}

	public boolean hasCustomInventoryName() {
		return false;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void markDirty() {
	}

	public boolean isUseableByPlayer(final EntityPlayer player) {
		return true;
	}

	public void openInventory() {
	}

	public void closeInventory() {
	}

	public boolean isItemValidForSlot(final int index, final ItemStack stack) {
		return true;
	}
}
