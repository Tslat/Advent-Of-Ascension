package net.tslat.aoa3.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoAItems;

import javax.annotation.Nullable;

public class FrameBenchContainer extends Container {
	private final Inventory input;
	private final CraftResultInventory output;
	private final IWorldPosCallable functionCaller;

	private Item currentSelection = AoAItems.HELMET_FRAME.get();

	public FrameBenchContainer(int id, PlayerInventory inventory) {
		this(id, inventory, IWorldPosCallable.DUMMY);
	}

	public FrameBenchContainer(int screenId, PlayerInventory plInventory, IWorldPosCallable functionCaller) {
		super(AoAContainers.FRAME_BENCH.get(), screenId);

		this.functionCaller = functionCaller;
		output = new CraftResultInventory();
		input = new Inventory(1) {
			@Override
			public void markDirty() {
				super.markDirty();

				FrameBenchContainer.this.onCraftMatrixChanged(this);
			}

			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack.getItem() == AoAItems.SCRAP_METAL.get();
			}
		};

		addSlot(new Slot(output, 0, 149, 34) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}

			@Override
			public boolean canTakeStack(PlayerEntity playerIn) {
				return getHasStack();
			}

			@Override
			public ItemStack onTake(PlayerEntity player, ItemStack stack) {
				FrameBenchContainer.this.input.decrStackSize(0, 1);

				return stack;
			}
		});

		addSlot(new Slot(input, 0, 11, 34) {
			@Override
			public boolean isItemValid(ItemStack stack) {
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
	public void onCraftMatrixChanged(IInventory inventory) {
		functionCaller.consume((world, pos) -> updateOutput());
	}

	@Override
	public void onContainerClosed(PlayerEntity player) {
		super.onContainerClosed(player);

		functionCaller.consume((world, pos) -> clearContainer(player, world, input));
	}

	public void changeSelection(String selection) {
		switch (selection) {
			case "crossbow":
				currentSelection = AoAItems.CROSSBOW_FRAME.get();
				break;
			case "blaster":
				currentSelection = AoAItems.BLASTER_FRAME.get();
				break;
			case "boots":
				currentSelection = AoAItems.BOOTS_FRAME.get();
				break;
			case "cannon":
				currentSelection = AoAItems.CANNON_FRAME.get();
				break;
			case "chestplate":
				currentSelection = AoAItems.CHESTPLATE_FRAME.get();
				break;
			case "gun":
				currentSelection = AoAItems.GUN_FRAME.get();
				break;
			case "helmet":
				currentSelection = AoAItems.HELMET_FRAME.get();
				break;
			case "leggings":
				currentSelection = AoAItems.LEGGINGS_FRAME.get();
				break;
			case "shotgun":
				currentSelection = AoAItems.SHOTGUN_FRAME.get();
				break;
			case "sniper":
				currentSelection = AoAItems.SNIPER_FRAME.get();
				break;
			default:
				currentSelection = AoAItems.HELMET_FRAME.get();
				break;
		}

		updateOutput();
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index == 0) {
				slotStack.getItem().onCreated(slotStack, player.world, player);

				if (!mergeItemStack(slotStack, 2, 38, true))
					return ItemStack.EMPTY;
			}
			else if (index >= 2 && index < 29) {
				if (!mergeItemStack(slotStack, 1, 2, false) && !mergeItemStack(slotStack, 29, 38, false))
					return ItemStack.EMPTY;
			}
			else if (index >= 29 && index < 38) {
				if (!mergeItemStack(slotStack, 1, 2, false) && !mergeItemStack(slotStack, 2, 29, false))
					return ItemStack.EMPTY;
			}
			else if (!mergeItemStack(slotStack, 2, 38, false)) {
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

			if (index == 0)
				player.dropItem(slot.onTake(player, slotStack), false);
		}

		return stack;
	}

	private void updateOutput() {
		if (input.isEmpty()) {
			output.setInventorySlotContents(0, ItemStack.EMPTY);
		}
		else {
			output.setInventorySlotContents(0, new ItemStack(currentSelection, 1));
		}

		detectAndSendChanges();
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(functionCaller, player, AoABlocks.FRAME_BENCH.get());
	}

	public static void openContainer(ServerPlayerEntity player, BlockPos pos) {
		NetworkHooks.openGui(player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("container.aoa3.frame_bench");
			}

			@Nullable
			@Override
			public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
				return new FrameBenchContainer(windowId, inv, IWorldPosCallable.of(player.world, pos));
			}
		}, pos);
	}
}