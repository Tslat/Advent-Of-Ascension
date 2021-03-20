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
			public void setChanged() {
				super.setChanged();

				slotsChanged(this);
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
	public void slotsChanged(IInventory inventory) {
		functionCaller.execute((world, pos) -> updateOutput());
	}

	@Override
	public void removed(PlayerEntity playerIn) {
		super.removed(playerIn);

		functionCaller.execute((world, pos) -> clearContainer(playerIn, world, inputs));
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return stillValid(functionCaller, player, getBlock());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index == 2) {
				functionCaller.execute((world, pos) -> slotStack.getItem().onCraftedBy(slotStack, world, player));

				if (!moveItemStackTo(slotStack, 3, 39, true))
					return ItemStack.EMPTY;

				slot.onQuickCraft(slotStack, stack);
			}
			else if (index != 0 && index != 1) {
				if (index < 39 && !moveItemStackTo(slotStack, 0, 2, false))
					return ItemStack.EMPTY;
			}
			else if (!moveItemStackTo(slotStack, 3, 39, false)) {
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
