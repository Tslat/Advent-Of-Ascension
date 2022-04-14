package net.tslat.aoa3.common.container;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.npc.banker.AoABanker;

public class BankerContainer extends AbstractContainerMenu {
	private final Container inputs;
	private final Container outputs;

	public final AoABanker banker;
	private final Player player;

	public BankerContainer(int screenId, Inventory playerInventory) {
		this(screenId, playerInventory, null);
	}

	public BankerContainer(int screenId, Inventory playerInventory, AoABanker banker) {
		super(AoAContainers.BANKER.get(), screenId);

		this.player = playerInventory.player;
		this.banker = banker;

		inputs = new SimpleContainer(6) {
			@Override
			public boolean canPlaceItem(int index, ItemStack stack) {
				return stack.getItem() == getCoinForSlot(index);
			}
		};

		outputs = new SimpleContainer(6) {
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
				public boolean mayPickup(Player playerIn) {
					return hasItem();
				}

				@Override
				public void onTake(Player thePlayer, ItemStack stack) {
					inputs.getItem(getSlotIndex()).shrink(getSlotIndex() < 3 ? 20 : 1);
					updateOutput(getSlotIndex());
				}
			});
		}

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlot(new Slot(player.getInventory(), inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 105 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(player.getInventory(), hotbarSlot, 8 + hotbarSlot * 18, 163));
		}
	}

	@Override
	public void removed(Player player) {
		super.removed(player);

		if (!player.level.isClientSide)
			clearContainer(player, inputs);
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
	public ItemStack quickMoveStack(Player player, int index) {
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
	public boolean stillValid(Player player) {
		return banker != null && banker.isAlive() && this.player.distanceToSqr(banker) <= 64;
	}

	public static Item getCoinForSlot(int index) {
		return switch (index) {
			case 0, 9 -> AoAItems.COPPER_COIN.get();
			case 1, 3, 6, 10 -> AoAItems.SILVER_COIN.get();
			case 2, 4, 7, 11 -> AoAItems.GOLD_COIN.get();
			case 5, 8 -> AoAItems.LUNAVER_COIN.get();
			default -> Items.AIR;
		};
	}
}
