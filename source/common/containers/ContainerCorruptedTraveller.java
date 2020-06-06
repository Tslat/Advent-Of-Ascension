package net.tslat.aoa3.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.npcs.trader.EntityCorruptedTraveller;

public class ContainerCorruptedTraveller extends Container {
	private final InventoryBasic input;

	private final EntityCorruptedTraveller traveller;
	private final EntityPlayer player;

	private boolean handledFood = false;

	public ContainerCorruptedTraveller(EntityPlayer player, EntityCorruptedTraveller traveller) {
		this.traveller = traveller;
		this.player = player;

		input = new InventoryBasic("CorruptedTraveller", true, 1) {
			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack.getItem() instanceof ItemFood;
			}
		};

		addSlotToContainer(new Slot(input, 0, 80, 34) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem() instanceof ItemFood;
			}

			@Override
			public void onSlotChanged() {
				super.onSlotChanged();

				if (getHasStack())
					handleFoodInput();
			}
		});

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlotToContainer(new Slot(player.inventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 65 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlotToContainer(new Slot(player.inventory, hotbarSlot, 8 + hotbarSlot * 18, 123));
		}
	}

	private void handleFoodInput() {
		if (!handledFood) {
			ItemStack stack = inventorySlots.get(0).inventory.getStackInSlot(0);

			if (!stack.isEmpty() && stack.getItem() instanceof ItemFood && player.inventory.addItemStackToInventory(new ItemStack(ItemRegister.WORN_BOOK)))
				stack.shrink(1);

			handledFood = true;
		}
		else {
			handledFood = false;
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!player.world.isRemote)
			clearContainer(player, player.world, input);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index != 0) {
				if (index < 28) {
					if (!mergeItemStack(slotStack, 28, 36, true))
						return ItemStack.EMPTY;

					slot.onSlotChange(slotStack, stack);
				}
				else if (index < 37 && !mergeItemStack(slotStack, 1, 27, false)) {
					return ItemStack.EMPTY;
				}
			}
			else if (!mergeItemStack(slotStack, 1, 36, false)) {
				return ItemStack.EMPTY;
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
		return traveller != null && traveller.isEntityAlive() && this.player.getDistanceSq(traveller) <= 64;
	}
}
