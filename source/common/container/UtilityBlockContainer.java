package net.tslat.aoa3.common.container;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public abstract class UtilityBlockContainer extends AbstractContainerMenu {
	public Container inputs;
	public final ResultContainer output;
	private final ContainerLevelAccess functionCaller;

	public UtilityBlockContainer(MenuType<?> type, int id, Inventory plInventory, ContainerLevelAccess functionCaller) {
		super(type, id);

		this.functionCaller = functionCaller;
		inputs = new SimpleContainer(2) {
			@Override
			public void setChanged() {
				super.setChanged();

				slotsChanged(this);
			}
		};

		output = new ResultContainer();

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
	public void slotsChanged(Container inventory) {
		functionCaller.execute((world, pos) -> updateOutput());
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);

		functionCaller.execute((world, pos) -> clearContainer(playerIn, inputs));
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(functionCaller, player, getBlock());
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
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
