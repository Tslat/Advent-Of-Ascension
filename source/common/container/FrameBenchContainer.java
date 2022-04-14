package net.tslat.aoa3.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.item.AoAItems;

import javax.annotation.Nullable;

public class FrameBenchContainer extends AbstractContainerMenu {
	private Container input;
	private final ResultContainer output;
	private ContainerLevelAccess functionCaller;

	private Item currentSelection = AoAItems.HELMET_FRAME.get();

	public FrameBenchContainer(int id, Inventory inventory) {
		this(id, inventory, ContainerLevelAccess.NULL);
	}

	public FrameBenchContainer(int screenId, Inventory plInventory, ContainerLevelAccess functionCaller) {
		super(AoAContainers.FRAME_BENCH.get(), screenId);

		this.functionCaller = functionCaller;
		output = new ResultContainer();
		input = new SimpleContainer(1) {
			@Override
			public void setChanged() {
				super.setChanged();

				FrameBenchContainer.this.slotsChanged(this);
			}

			@Override
			public boolean canPlaceItem(int index, ItemStack stack) {
				return stack.getItem() == AoAItems.SCRAP_METAL.get();
			}
		};

		addSlot(new Slot(output, 0, 149, 34) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			@Override
			public boolean mayPickup(Player playerIn) {
				return hasItem();
			}

			@Override
			public void onTake(Player player, ItemStack stack) {
				FrameBenchContainer.this.input.removeItem(0, 1);
			}
		});

		addSlot(new Slot(input, 0, 11, 34) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() == AoAItems.SCRAP_METAL.get();
			}
		});

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlot(new Slot(plInventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 84 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(plInventory, hotbarSlot, 8 + hotbarSlot * 18, 142));
		}
	}

	@Override
	public void slotsChanged(Container inventory) {
		functionCaller.execute((world, pos) -> updateOutput());
	}

	@Override
	public void removed(Player player) {
		super.removed(player);

		functionCaller.execute((world, pos) -> clearContainer(player, input));
	}

	public void changeSelection(String selection) {
		switch (selection) {
			case "crossbow" -> currentSelection = AoAItems.CROSSBOW_FRAME.get();
			case "blaster" -> currentSelection = AoAItems.BLASTER_FRAME.get();
			case "boots" -> currentSelection = AoAItems.BOOTS_FRAME.get();
			case "cannon" -> currentSelection = AoAItems.CANNON_FRAME.get();
			case "chestplate" -> currentSelection = AoAItems.CHESTPLATE_FRAME.get();
			case "gun" -> currentSelection = AoAItems.GUN_FRAME.get();
			case "helmet" -> currentSelection = AoAItems.HELMET_FRAME.get();
			case "leggings" -> currentSelection = AoAItems.LEGGINGS_FRAME.get();
			case "shotgun" -> currentSelection = AoAItems.SHOTGUN_FRAME.get();
			case "sniper" -> currentSelection = AoAItems.SNIPER_FRAME.get();
			default -> currentSelection = AoAItems.HELMET_FRAME.get();
		}

		updateOutput();
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index == 0) {
				slotStack.getItem().onCraftedBy(slotStack, player.level, player);

				if (!moveItemStackTo(slotStack, 2, 38, true))
					return ItemStack.EMPTY;
			}
			else if (index >= 2 && index < 29) {
				if (!moveItemStackTo(slotStack, 1, 2, false) && !moveItemStackTo(slotStack, 29, 38, false))
					return ItemStack.EMPTY;
			}
			else if (index >= 29 && index < 38) {
				if (!moveItemStackTo(slotStack, 1, 2, false) && !moveItemStackTo(slotStack, 2, 29, false))
					return ItemStack.EMPTY;
			}
			else if (!moveItemStackTo(slotStack, 2, 38, false)) {
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

			if (index == 0)
				player.drop(slotStack, false);
		}

		return stack;
	}

	private void updateOutput() {
		if (input.isEmpty()) {
			output.setItem(0, ItemStack.EMPTY);
		}
		else {
			output.setItem(0, new ItemStack(currentSelection, 1));
		}

		broadcastChanges();
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(functionCaller, player, AoABlocks.FRAME_BENCH.get());
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		NetworkHooks.openGui(player, new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return new TranslatableComponent("container.aoa3.frame_bench");
			}

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
				return new FrameBenchContainer(windowId, inv, ContainerLevelAccess.create(player.level, pos));
			}
		}, pos);
	}
}