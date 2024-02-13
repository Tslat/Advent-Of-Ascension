package net.tslat.aoa3.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.common.menu.generic.ExtensibleContainerMenu;
import net.tslat.aoa3.common.menu.provider.GenericMenuProvider;
import net.tslat.aoa3.common.menu.slot.OutputSlot;
import net.tslat.aoa3.common.menu.slot.PredicatedSlot;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;

public class WhitewashingTableMenu extends ExtensibleContainerMenu<TransientCraftingContainer> {
	public WhitewashingTableMenu(int screenId, Inventory playerInventory, ContainerLevelAccess accessValidator) {
		super(AoAMenus.WHITEWASHING_TABLE.get(), screenId, playerInventory, accessValidator);

		createPlayerInventory(playerInventory, 8, 60);
	}

	@Override
    public int inputSlotCount() {
		return 2;
	}

	@Override
	protected TransientCraftingContainer createInventory() {
		return new TransientCraftingContainer(this, 2, 1);
	}

	@Override
	protected Block getContainerBlock() {
		return AoABlocks.WHITEWASHING_TABLE.get();
	}

	@Override
	protected Slot createInputSlot(int slotIndex, TransientCraftingContainer inventory) {
		final int slotX = slotIndex == 0 ? 27 : 76;

		return slotIndex == 0 ?
				new PredicatedSlot(inventory, slotIndex, slotX, 23, stack -> stack.getItem() == Items.OBSIDIAN) :
				new PredicatedSlot(inventory, slotIndex, slotX, 23, stack -> stack.getItem() == AoAItems.WHITEWASHING_SOLUTION.get() || stack.getItem() == AoAItems.DARKLY_POWDER.get());
	}

	@Override
	protected Slot createOutputSlot(int slotIndex, Player player) {
		return new OutputSlot(new ResultContainer(), player, slotIndex, 134, 23) {
			@Override
			public void onItemRemoved(Player player, ItemStack stack) {
				consumeInputItem(0, 1);
				consumeInputItem(1, 1);
			}
		};
	}

	@Override
	protected void handleContainerUpdate() {
		final ItemStack brickStack = getInputItem(0);
		final ItemStack powderStack = getInputItem(1);

		if (!powderStack.isEmpty() && brickStack.getItem() == Items.OBSIDIAN) {
			if (powderStack.getItem() == AoAItems.DARKLY_POWDER.get()) {
				setOutputItem(new ItemStack(AoABlocks.DARKWASH_BRICKS.stone(), 2));
			}
			else if (powderStack.getItem() == AoAItems.WHITEWASHING_SOLUTION.get()) {
				setOutputItem(new ItemStack(AoABlocks.WHITEWASH_BRICKS.stone(), 2));
			}
			else {
				setOutputItem(ItemStack.EMPTY);
			}
		}
		else {
			setOutputItem(ItemStack.EMPTY);
		}
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		player.openMenu(new GenericMenuProvider("whitewashing_table", pos, WhitewashingTableMenu::new), pos);
	}
}
