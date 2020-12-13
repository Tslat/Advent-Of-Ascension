package net.tslat.aoa3.common.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;

public abstract class UtilityBlockContainer extends Container {
	public final Inventory inputs;
	public final CraftResultInventory output;
	private final IWorldPosCallable functionCaller;

	public UtilityBlockContainer(ContainerType<?> type, int id, PlayerInventory plInventory, IWorldPosCallable functionCaller) {
		super(type, id);

		this.functionCaller = functionCaller;
		inputs = new Inventory(2) {
			@Override
			public void markDirty() {
				super.markDirty();

				onCraftMatrixChanged(this);
			}
		};

		output = new CraftResultInventory();

		addSlot(initFirstInputSlot());
		addSlot(initSecondInputSlot());
		addSlot(initOutputSlot());

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				addSlot(new Slot(plInventory, col + row * 9 + 9, 8 + col * 18, 60 + row * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			addSlot(new Slot(plInventory, x, 8 + x * 18, 118));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		functionCaller.consume((world, pos) -> updateOutput());
	}

	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);

		functionCaller.consume((world, pos) -> clearContainer(playerIn, world, inputs));
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(functionCaller, player, getBlock());
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index == 2) {
				functionCaller.consume((world, pos) -> slotStack.getItem().onCreated(slotStack, world, player));

				if (!mergeItemStack(slotStack, 3, 39, true))
					return ItemStack.EMPTY;

				slot.onSlotChange(slotStack, stack);
			}
			else if (index != 0 && index != 1) {
				if (index < 39 && !mergeItemStack(slotStack, 0, 2, false))
					return ItemStack.EMPTY;
			}
			else if (!mergeItemStack(slotStack, 3, 39, false)) {
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

	public abstract void updateOutput();

	protected Slot initFirstInputSlot() {
		return new Slot(inputs, 0, 27, 23);
	}

	protected Slot initSecondInputSlot() {
		return new Slot(inputs, 1, 76, 23);
	}

	protected abstract Slot initOutputSlot();

	protected abstract Block getBlock();
}
