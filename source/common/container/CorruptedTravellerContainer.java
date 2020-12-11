package net.tslat.aoa3.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.npc.trader.CorruptedTravellerEntity;

public class CorruptedTravellerContainer extends Container {
	private final Inventory input;

	public final CorruptedTravellerEntity traveller;
	private final PlayerEntity player;

	private boolean handledFood = false;

	public CorruptedTravellerContainer(int screenId, PlayerInventory playerInventory) {
		this(screenId, playerInventory, null);
	}

	public CorruptedTravellerContainer(int screenId, PlayerInventory playerInventory, CorruptedTravellerEntity traveller) {
		super(AoAContainers.CORRUPTED_TRAVELLER.get(), screenId);

		this.traveller = traveller;
		this.player = playerInventory.player;

		input = new Inventory(1) {
			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack.getItem().getFood() != null;
			}
		};

		addSlot(new Slot(input, 0, 80, 34) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem().getFood() != null;
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
				addSlot(new Slot(player.inventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 65 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(player.inventory, hotbarSlot, 8 + hotbarSlot * 18, 123));
		}
	}

	private void handleFoodInput() {
		if (!handledFood) {
			ItemStack stack = inventorySlots.get(0).inventory.getStackInSlot(0);

			if (!stack.isEmpty() && stack.getItem().getFood() != null && player.inventory.addItemStackToInventory(new ItemStack(AoAItems.WORN_BOOK.get())))
				stack.shrink(1);

			handledFood = true;
		}
		else {
			handledFood = false;
		}
	}

	@Override
	public void onContainerClosed(PlayerEntity player) {
		super.onContainerClosed(player);

		if (!player.world.isRemote)
			clearContainer(player, player.world, input);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
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
	public boolean canInteractWith(PlayerEntity player) {
		return traveller != null && traveller.isAlive() && this.player.getDistanceSq(traveller) <= 64;
	}
}
