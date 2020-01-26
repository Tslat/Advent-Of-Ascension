package net.tslat.aoa3.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;

public class ContainerBankerTrade extends Container {
	private final InventoryBasic inputs;
	private final InventoryBasic outputs;

	private final AoATrader banker;
	private final EntityPlayer player;

	public ContainerBankerTrade(EntityPlayer player, AoATrader banker) {
		this.player = player;
		this.banker = banker;

		inputs = new InventoryBasic(banker.getDisplayName().getUnformattedComponentText(), true, 6) {
			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack.getItem() == getCoinForSlot(index);
			}
		};

		outputs = new InventoryBasic(banker.getDisplayName().getUnformattedComponentText(), true, 6) {
			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack.getItem() == getCoinForSlot(index + 6);
			}
		};

		for (int i = 0; i < 6; i++) {
			addSlotToContainer(new Slot(inputs, i, i < 3 ? 15 : 101, 28 + i % 3 * 23) {
				@Override
				public boolean isItemValid(ItemStack stack) {
					return stack.getItem() == getCoinForSlot(getSlotIndex());
				}

				@Override
				public void onSlotChanged() {
					super.onSlotChanged();

					updateOutput(getSlotIndex());
				}
			});
		}

		for (int i = 0; i < 6; i++) {
			addSlotToContainer(new Slot(outputs, i, i < 3 ? 58 : 144, 28 + i % 3 * 23) {
				@Override
				public boolean isItemValid(ItemStack stack) {
					return false;
				}

				@Override
				public boolean canTakeStack(EntityPlayer playerIn) {
					return getHasStack();
				}

				@Override
				public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
					inputs.getStackInSlot(getSlotIndex()).shrink(getSlotIndex() < 3 ? 20 : 1);
					updateOutput(getSlotIndex());

					return stack;
				}
			});
		}

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlotToContainer(new Slot(player.inventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 105 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlotToContainer(new Slot(player.inventory, hotbarSlot, 8 + hotbarSlot * 18, 163));
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!player.world.isRemote)
			clearContainer(player, player.world, inputs);
	}

	protected void updateOutput(int index) {
		ItemStack slotStack = inputs.getStackInSlot(index);

		if (!slotStack.isEmpty() && (index >= 3 || slotStack.getCount() >= 20)) {
			outputs.setInventorySlotContents(index, new ItemStack(getCoinForSlot(index + 6), index < 3 ? 1 : 20));
		}
		else {
			outputs.setInventorySlotContents(index, ItemStack.EMPTY);
		}

		detectAndSendChanges();
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index < 12) {
				if (!mergeItemStack(slotStack, 12, 47, false))
					return ItemStack.EMPTY;
			}
			else {
				for (int i = 0; i < 6 && !slotStack.isEmpty(); i++) {
					Slot slot2 = inventorySlots.get(i);

					if (slot2.getHasStack() && slot2.getStack().getItem() == slotStack.getItem())
						mergeItemStack(slotStack, i, i + 1, false);
				}

				if (!slotStack.isEmpty()) {
					if (index < 39) {
						if (!mergeItemStack(slotStack, 39, 47, true))
							return ItemStack.EMPTY;
					}
					else {
						if (!mergeItemStack(slotStack, 12, 38, false))
							return ItemStack.EMPTY;
					}
				}
			}

			if (slotStack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}

			if (slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, slotStack);
		}

		return stack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return banker != null && banker.isEntityAlive() && this.player.getDistanceSq(banker) <= 64;
	}

	public static Item getCoinForSlot(int index) {
		switch (index) {
			case 0:
			case 9:
				return ItemRegister.coinCopper;
			case 1:
			case 3:
			case 6:
			case 10:
				return ItemRegister.coinSilver;
			case 2:
			case 4:
			case 7:
			case 11:
				return ItemRegister.coinGold;
			case 5:
			case 8:
				return ItemRegister.coinLunaver;
			default:
				return Items.AIR;
		}
	}
}
