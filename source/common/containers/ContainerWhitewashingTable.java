package net.tslat.aoa3.common.containers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;

public class ContainerWhitewashingTable extends ContainerBasicUtility {
	public ContainerWhitewashingTable(EntityPlayer player, World world, BlockPos pos) {
		super(player, world, pos);
	}

	@Override
	protected Slot initFirstInputSlot() {
		return new Slot(inputs, 0, 27, 23) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem() == ItemBlock.getItemFromBlock(Blocks.OBSIDIAN);
			}
		};
	}

	@Override
	protected Slot initSecondInputSlot() {
		return new Slot(inputs, 1, 76, 23) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem() == ItemRegister.whitewashingSolution || stack.getItem() == ItemRegister.darklyPowder;
			}
		};
	}

	@Override
	protected Slot initOutputSlot() {
		return new Slot(output, 2, 134, 23) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}

			@Override
			public boolean canTakeStack(EntityPlayer player) {
				return getHasStack();
			}

			@Override
			public ItemStack onTake(EntityPlayer player, ItemStack stack) {
				inputs.getStackInSlot(0).shrink(1);
				inputs.getStackInSlot(1).shrink(1);

				onCraftMatrixChanged(inputs);
				return stack;
			}
		};
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!player.world.isRemote)
			clearContainer(player, player.world, inputs);
	}

	@Override
	public void updateOutput() {
		ItemStack brickStack = inputs.getStackInSlot(0);
		ItemStack powderStack = inputs.getStackInSlot(1);

		if (!powderStack.isEmpty() && brickStack.getItem() == ItemBlock.getItemFromBlock(Blocks.OBSIDIAN)) {
			if (powderStack.getItem() == ItemRegister.darklyPowder) {
				output.setInventorySlotContents(0, new ItemStack(BlockRegister.bricksDarkwash, 2));
			}
			else if (powderStack.getItem() == ItemRegister.whitewashingSolution) {
				output.setInventorySlotContents(0, new ItemStack(BlockRegister.bricksWhitewash, 2));
			}
			else {
				output.setInventorySlotContents(0, ItemStack.EMPTY);
			}
		}
		else {
			output.setInventorySlotContents(0, ItemStack.EMPTY);
		}
	}

	@Override
	protected Block getBlock() {
		return BlockRegister.whitewashingTable;
	}

	@Override
	protected String getGuiTitle() {
		return "Whitewashing";
	}
}
