package net.tslat.aoa3.common.containers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ContainerBasicUtility extends Container {
	public final InventoryBasic inputs;
	public final InventoryCraftResult output;

	private final World world;
	private final BlockPos pos;
	private final EntityPlayer player;

	public ContainerBasicUtility(EntityPlayer player, World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
		this.player = player;

		inputs = new InventoryBasic(getGuiTitle(), true, 2) {
			@Override
			public void markDirty() {
				super.markDirty();

				onCraftMatrixChanged(this);
			}
		};

		output = new InventoryCraftResult();

		addSlotToContainer(initFirstInputSlot());
		addSlotToContainer(initSecondInputSlot());
		addSlotToContainer(initOutputSlot());

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				addSlotToContainer(new Slot(player.inventory, col + row * 9 + 9, 8 + col * 18, 60 + row * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(player.inventory, x, 8 + x * 18, 118));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		if (inventory == inputs)
			updateOutput();

		super.onCraftMatrixChanged(inventory);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index == 2) {
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

	protected abstract String getGuiTitle();

	protected abstract Block getBlock();

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (world.getBlockState(pos).getBlock() != getBlock())
			return false;

		return this.player.getDistanceSq(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d) <= 64;
	}
}
