package net.tslat.aoa3.common.container;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.npc.trader.CorruptedTravellerEntity;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.patchouli.PatchouliIntegration;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.ItemUtil;

public class CorruptedTravellerContainer extends AbstractContainerMenu {
	private Inventory input;

	public final CorruptedTravellerEntity traveller;
	private final Player player;

	private boolean handledFood = false;

	public CorruptedTravellerContainer(int screenId, Inventory playerInventory) {
		this(screenId, playerInventory, null);
	}

	public CorruptedTravellerContainer(int screenId, Inventory playerInventory, CorruptedTravellerEntity traveller) {
		super(AoAContainers.CORRUPTED_TRAVELLER.get(), screenId);

		this.traveller = traveller;
		this.player = playerInventory.player;

		/*input = new Inventory(1) {
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
				addSlot(new Slot(player.getInventory(), inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 65 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(player.getInventory(), hotbarSlot, 8 + hotbarSlot * 18, 123));
		}*/
	}

	private void handleFoodInput() {
		if (!handledFood) {
			ItemStack stack = slots.get(0).container.getItem(0);

			if (!stack.isEmpty() && stack.getItem().getFoodProperties() != null) {
				ItemStack bookStack;

				if (IntegrationManager.isPatchouliActive()) {
					bookStack = PatchouliIntegration.getBook(AdventOfAscension.id("worn_book")).copy();

					if (player instanceof ServerPlayer)
						AdvancementUtil.completeAdvancement((ServerPlayer)player, AdventOfAscension.id("overworld/the_journey_begins"), "obtain_worn_book");

					ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()));
				}
				else {
					bookStack = new ItemStack(AoAItems.WORN_BOOK.get());
				}

				ItemUtil.givePlayerItemOrDrop(player, bookStack);
				stack.shrink(1);
			}

			handledFood = true;
		}
		else {
			handledFood = false;
		}
	}

	@Override
	public void removed(Player player) {
		super.removed(player);

		if (!player.level.isClientSide)
			clearContainer(player, input);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
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
	public boolean stillValid(Player player) {
		return traveller != null && traveller.isAlive() && this.player.distanceToSqr(traveller) <= 64;
	}
}
