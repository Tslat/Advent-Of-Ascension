package net.tslat.aoa3.common.container;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BlockItem;
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

public class WhitewashingTableContainer extends UtilityBlockContainer {
	public WhitewashingTableContainer(int screenId, PlayerInventory plInventory) {
		this(screenId, plInventory, IWorldPosCallable.DUMMY);
	}

	public WhitewashingTableContainer(int screenId, PlayerInventory plInventory, IWorldPosCallable functionCaller) {
		super(AoAContainers.WHITEWASHING_TABLE.get(), screenId, plInventory, functionCaller);
	}

	@Override
	protected Slot initFirstInputSlot() {
		return new Slot(inputs, 0, 27, 23) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem() == BlockItem.getItemFromBlock(Blocks.OBSIDIAN);
			}
		};
	}

	@Override
	protected Slot initSecondInputSlot() {
		return new Slot(inputs, 1, 76, 23) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return stack.getItem() == AoAItems.WHITEWASHING_SOLUTION.get() || stack.getItem() == AoAItems.DARKLY_POWDER.get();
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
			public boolean canTakeStack(PlayerEntity player) {
				return getHasStack();
			}

			@Override
			public ItemStack onTake(PlayerEntity player, ItemStack stack) {
				inputs.getStackInSlot(0).shrink(1);
				inputs.getStackInSlot(1).shrink(1);

				onCraftMatrixChanged(inputs);
				return stack;
			}
		};
	}

	@Override
	public void updateOutput() {
		ItemStack brickStack = inputs.getStackInSlot(0);
		ItemStack powderStack = inputs.getStackInSlot(1);

		if (!powderStack.isEmpty() && brickStack.getItem() == BlockItem.getItemFromBlock(Blocks.OBSIDIAN)) {
			if (powderStack.getItem() == AoAItems.DARKLY_POWDER.get()) {
				output.setInventorySlotContents(0, new ItemStack(AoABlocks.DARKWASH_BRICKS.get(), 2));
			}
			else if (powderStack.getItem() == AoAItems.WHITEWASHING_SOLUTION.get()) {
				output.setInventorySlotContents(0, new ItemStack(AoABlocks.WHITEWASH_BRICKS.get(), 2));
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
		return AoABlocks.WHITEWASHING_TABLE.get();
	}

	public static void openContainer(ServerPlayerEntity player, BlockPos pos) {
		NetworkHooks.openGui(player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("container.aoa3.whitewashing_table");
			}

			@Nullable
			@Override
			public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
				return new WhitewashingTableContainer(windowId, inv, IWorldPosCallable.of(player.world, pos));
			}
		}, pos);
	}
}
