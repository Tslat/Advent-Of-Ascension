package net.tslat.aoa3.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.object.entity.npc.banker.AoABanker;

public class BankerContainer extends Container {
	private final Inventory inputs;
	private final Inventory outputs;

	public final AoABanker banker;
	private final PlayerEntity player;

	public BankerContainer(int screenId, PlayerInventory playerInventory) {
		this(screenId, playerInventory, null);
	}

	public BankerContainer(int screenId, PlayerInventory playerInventory, AoABanker banker) {
		super(AoAContainers.BANKER.get(), screenId);

		this.player = playerInventory.player;
		this.banker = banker;

		inputs = new Inventory(6) {
			@Override
			public boolean canPlaceItem(int index, ItemStack stack) {
				return stack.getItem() == getCoinForSlot(index);
			}
		};

		outputs = new Inventory(6) {
			@Override
			public boolean canPlaceItem(int index, ItemStack stack) {
				return stack.getItem() == getCoinForSlot(index + 6);
			}
		};

		for (int i = 0; i < 6; i++) {
			addSlot(new Slot(inputs, i, i < 3 ? 15 : 101, 28 + i % 3 * 23) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return stack.getItem() == getCoinForSlot(getSlotIndex());
				}

				@Override
				public void setChanged() {
					super.setChanged();

					updateOutput(getSlotIndex());
				}
			});
		}

		for (int i = 0; i < 6; i++) {
			addSlot(new Slot(outputs, i, i < 3 ? 58 : 144, 28 + i % 3 * 23) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					return false;
				}

				@Override
				public boolean mayPickup(PlayerEntity playerIn) {
					return hasItem();
				}

				@Override
				public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
					inputs.getItem(getSlotIndex()).shrink(getSlotIndex() < 3 ? 20 : 1);
					updateOutput(getSlotIndex());

					return stack;
				}
			});
		}

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlot(new Slot(player.inventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 105 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(player.inventory, hotbarSlot, 8 + hotbarSlot * 18, 163));
		}
	}

	@Override
	public void removed(PlayerEntity player) {
		super.removed(player);

		if (!player.level.isClientSide)
			clearContainer(player, player.level, inputs);
	}

	protected void updateOutput(int index) {
		ItemStack slotStack = inputs.getItem(index);

		if (!slotStack.isEmpty() && (index >= 3 || slotStack.getCount() >= 20)) {
			outputs.setItem(index, new ItemStack(getCoinForSlot(index + 6), index < 3 ? 1 : 20));
		}
		else {
			outputs.setItem(index, ItemStack.EMPTY);
		}

		broadcastChanges();
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index < 12) {
				if (!moveItemStackTo(slotStack, 12, 47, false))
					return ItemStack.EMPTY;
			}
			else {
				for (int i = 0; i < 6 && !slotStack.isEmpty(); i++) {
					Slot slot2 = slots.get(i);

					if (slot2.hasItem() && slot2.getItem().getItem() == slotStack.getItem())
						moveItemStackTo(slotStack, i, i + 1, false);
				}

				if (!slotStack.isEmpty()) {
					if (index < 39) {
						if (!moveItemStackTo(slotStack, 39, 47, true))
							return ItemStack.EMPTY;
					}
					else {
						if (!moveItemStackTo(slotStack, 12, 38, false))
							return ItemStack.EMPTY;
					}
				}
			}

			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}
			else {
				slot.setChanged();
			}

			if (slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, slotStack);
		}

		return stack;
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return banker != null && banker.isAlive() && this.player.distanceToSqr(banker) <= 64;
	}

	public static Item getCoinForSlot(int index) {
		switch (index) {
			case 0:
			case 9:
				return AoAItems.COPPER_COIN.get();
			case 1:
			case 3:
			case 6:
			case 10:
				return AoAItems.SILVER_COIN.get();
			case 2:
			case 4:
			case 7:
			case 11:
				return AoAItems.GOLD_COIN.get();
			case 5:
			case 8:
				return AoAItems.LUNAVER_COIN.get();
			default:
				return Items.AIR;
		}
	}
}
