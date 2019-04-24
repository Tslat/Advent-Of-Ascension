package net.nevermine.gui.pouch;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.nevermine.item.ItemRune;

public class ContainerRunePouch extends Container {
	private IInventory inv;
	private int numRows;
	private int blockedSlot = -1;

	public ContainerRunePouch(final IInventory player, final IInventory inv) {
		this.inv = inv;
		numRows = inv.getSizeInventory() / 9;
		inv.openInventory();
		InventoryPlayer plInv = (InventoryPlayer)player;
		final int i = (numRows - 4) * 18;
		for (int j = 0; j < numRows; ++j) {
			for (int k = 0; k < 9; ++k) {
				addSlotToContainer(new SlotRunePouch(inv, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}
		for (int j = 0; j < 3; ++j) {
			for (int k = 0; k < 9; ++k) {
				addSlotToContainer(new Slot(player, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));

				if (plInv.currentItem == k + j + 9)
					blockedSlot = 18 + k + j;
			}
		}
		for (int j = 0; j < 9; ++j) {
			addSlotToContainer(new Slot(player, j, 8 + j * 18, 161 + i));

			if (plInv.currentItem == j)
				blockedSlot = 45 + j;
		}
	}

	public boolean canInteractWith(final EntityPlayer player) {
		return true;
	}

	public ItemStack transferStackInSlot(final EntityPlayer player, final int index) {
		ItemStack itemstack = null;
		final Slot slot = (Slot)inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			final ItemStack itemstack2 = slot.getStack();
			itemstack = itemstack2.copy();
			if (!(itemstack.getItem() instanceof ItemRune)) {
				return null;
			}
			if (index < numRows * 9) {
				if (!mergeItemStack(itemstack2, numRows * 9, inventorySlots.size(), true)) {
					return null;
				}
			}
			else if (!mergeItemStack(itemstack2, 0, numRows * 9, false)) {
				return null;
			}
			if (itemstack2.stackSize == 0) {
				slot.putStack(null);
			}
			else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}

	public void onContainerClosed(final EntityPlayer player) {
		super.onContainerClosed(player);
		if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemRunePouch) {
			final NBTTagList nbttaglist = new NBTTagList();
			for (int n = 0; n < inv.getSizeInventory(); ++n) {
				final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)n);
				if (inv.getStackInSlot(n) != null) {
					inv.getStackInSlot(n).writeToNBT(nbttagcompound1);
				}
				nbttaglist.appendTag(nbttagcompound1);
			}
			player.getHeldItem().getTagCompound().setTag("Inventory", nbttaglist);
		}
	}

	public ItemStack slotClick(int slotId, int button, int clickFlag, EntityPlayer player) {
		if (slotId == blockedSlot || clickFlag == 2)
		return null;

		return super.slotClick(slotId, button, clickFlag, player);
	}
}
