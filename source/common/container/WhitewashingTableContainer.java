package net.tslat.aoa3.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;

import javax.annotation.Nullable;

public class WhitewashingTableContainer extends UtilityBlockContainer {
	public WhitewashingTableContainer(int screenId, Inventory plInventory) {
		this(screenId, plInventory, ContainerLevelAccess.NULL);
	}

	public WhitewashingTableContainer(int screenId, Inventory plInventory, ContainerLevelAccess functionCaller) {
		super(AoAContainers.WHITEWASHING_TABLE.get(), screenId, plInventory, functionCaller);
	}

	@Override
	protected Slot initFirstInputSlot() {
		return new Slot(inputs, 0, 27, 23) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() == BlockItem.byBlock(Blocks.OBSIDIAN);
			}
		};
	}

	@Override
	protected Slot initSecondInputSlot() {
		return new Slot(inputs, 1, 76, 23) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return stack.getItem() == AoAItems.WHITEWASHING_SOLUTION.get() || stack.getItem() == AoAItems.DARKLY_POWDER.get();
			}
		};
	}

	@Override
	protected Slot initOutputSlot() {
		return new Slot(output, 2, 134, 23) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}

			@Override
			public boolean mayPickup(Player player) {
				return hasItem();
			}

			@Override
			public void onTake(Player player, ItemStack stack) {
				inputs.getItem(0).shrink(1);
				inputs.getItem(1).shrink(1);

				slotsChanged(inputs);
			}
		};
	}

	@Override
	public void updateOutput() {
		ItemStack brickStack = inputs.getItem(0);
		ItemStack powderStack = inputs.getItem(1);

		if (!powderStack.isEmpty() && brickStack.getItem() == BlockItem.byBlock(Blocks.OBSIDIAN)) {
			if (powderStack.getItem() == AoAItems.DARKLY_POWDER.get()) {
				output.setItem(0, new ItemStack(AoABlocks.DARKWASH_BRICKS.stone(), 2));
			}
			else if (powderStack.getItem() == AoAItems.WHITEWASHING_SOLUTION.get()) {
				output.setItem(0, new ItemStack(AoABlocks.WHITEWASH_BRICKS.stone(), 2));
			}
			else {
				output.setItem(0, ItemStack.EMPTY);
			}
		}
		else {
			output.setItem(0, ItemStack.EMPTY);
		}
	}

	@Override
	protected Block getBlock() {
		return AoABlocks.WHITEWASHING_TABLE.get();
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		NetworkHooks.openScreen(player, new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return Component.translatable("container.aoa3.whitewashing_table");
			}

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
				return new WhitewashingTableContainer(windowId, inv, ContainerLevelAccess.create(player.level(), pos));
			}
		}, pos);
	}
}
