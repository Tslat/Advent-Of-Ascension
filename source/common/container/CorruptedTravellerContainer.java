package net.tslat.aoa3.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.npc.trader.CorruptedTravellerEntity;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.util.AdvancementUtil;

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
			public boolean canPlaceItem(int index, ItemStack stack) {
				return stack.getItem().getFoodProperties() != null;
			}
		};

		addSlot(new Slot(input, 0, 80, 34) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem().getFoodProperties() != null;
			}

			@Override
			public void setChanged() {
				super.setChanged();

				if (hasItem())
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
			ItemStack stack = slots.get(0).container.getItem(0);
			ItemStack bookStack;

			if (IntegrationManager.isPatchouliActive()) {
				bookStack = PatchouliIntegration.getBook(AdventOfAscension.id("worn_book")).copy();

				if (player instanceof ServerPlayerEntity)
					AdvancementUtil.completeAdvancement((ServerPlayerEntity)player, AdventOfAscension.id("overworld/the_journey_begins"), "obtain_worn_book");
			}
			else {
				bookStack = new ItemStack(AoAItems.WORN_BOOK.get());
			}

			if (!stack.isEmpty() && stack.getItem().getFoodProperties() != null && player.inventory.add(bookStack))
				stack.shrink(1);

			handledFood = true;
		}
		else {
			handledFood = false;
		}
	}

	@Override
	public void removed(PlayerEntity player) {
		super.removed(player);

		if (!player.level.isClientSide)
			clearContainer(player, player.level, input);
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index != 0) {
				if (index < 28) {
					if (!moveItemStackTo(slotStack, 28, 36, true))
						return ItemStack.EMPTY;

					slot.onQuickCraft(slotStack, stack);
				}
				else if (index < 37 && !moveItemStackTo(slotStack, 1, 27, false)) {
					return ItemStack.EMPTY;
				}
			}
			else if (!moveItemStackTo(slotStack, 1, 36, false)) {
				return ItemStack.EMPTY;
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
		return traveller != null && traveller.isAlive() && this.player.distanceToSqr(traveller) <= 64;
	}
}
