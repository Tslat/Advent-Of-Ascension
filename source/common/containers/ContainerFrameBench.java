package net.tslat.aoa3.common.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.misc.FrameItem;

public class ContainerFrameBench extends Container {
	private final InventoryBasic input;
	private final InventoryCraftResult output;
	private FrameItem currentSelection = ItemRegister.HELMET_FRAME;

	private final World world;
	private final BlockPos pos;
	private final EntityPlayer player;

	public ContainerFrameBench(EntityPlayer player, World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
		this.player = player;

		output = new InventoryCraftResult();
		input = new InventoryBasic("FrameBench", true, 1) {
			@Override
			public void markDirty() {
				super.markDirty();

				ContainerFrameBench.this.onCraftMatrixChanged(this);
			}

			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack.getItem() == ItemRegister.SCRAP_METAL;
			}
		};

		addSlotToContainer(new Slot(output, 0, 149, 34) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}

			@Override
			public boolean canTakeStack(EntityPlayer playerIn) {
				return getHasStack();
			}

			@Override
			public ItemStack onTake(EntityPlayer player, ItemStack stack) {
				ContainerFrameBench.this.input.decrStackSize(0, 1);

				return stack;
			}
		});

		addSlotToContainer(new Slot(input, 0, 11, 34) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem() == ItemRegister.SCRAP_METAL;
			}
		});

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlotToContainer(new Slot(player.inventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 84 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlotToContainer(new Slot(player.inventory, hotbarSlot, 8 + hotbarSlot * 18, 142));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		super.onCraftMatrixChanged(inventory);

		if (inventory == input)
			updateOutput();
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!world.isRemote)
			clearContainer(player, world, input);
	}

	public void changeSelection(String selection) {
		switch (selection) {
			case "archergun":
				currentSelection = ItemRegister.ARCHERGUN_FRAME;
				break;
			case "blaster":
				currentSelection = ItemRegister.BLASTER_FRAME;
				break;
			case "boots":
				currentSelection = ItemRegister.BOOTS_FRAME;
				break;
			case "cannon":
				currentSelection = ItemRegister.CANNON_FRAME;
				break;
			case "chestplate":
				currentSelection = ItemRegister.CHESTPLATE_FRAME;
				break;
			case "gun":
				currentSelection = ItemRegister.GUN_FRAME;
				break;
			case "helmet":
				currentSelection = ItemRegister.HELMET_FRAME;
				break;
			case "leggings":
				currentSelection = ItemRegister.LEGGINGS_FRAME;
				break;
			case "shotgun":
				currentSelection = ItemRegister.SHOTGUN_FRAME;
				break;
			case "sniper":
				currentSelection = ItemRegister.SNIPER_FRAME;
				break;
			default:
				currentSelection = ItemRegister.HELMET_FRAME;
				break;
		}

		updateOutput();
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index == 0) {
				slotStack.getItem().onCreated(slotStack, world, this.player);

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
				this.player.dropItem(slot.onTake(this.player, slotStack), false);
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
	public boolean canInteractWith(EntityPlayer player) {
		if (world.getBlockState(pos).getBlock() != BlockRegister.FRAME_BENCH)
			return false;

		return this.player.getDistanceSq(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d) <= 64;
	}
}
