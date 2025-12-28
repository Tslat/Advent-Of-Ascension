package net.tslat.aoa3.content.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.menu.ImbuingChamberMenu;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.content.item.misc.AspectFocusItem;
import net.tslat.aoa3.library.object.extension.GenericItemStackHandler;

public class ImbuingChamberBlockEntity extends GenericContainerBlockEntity {
	private static final Component DEFAULT_NAME = Component.translatable("container." + AdventOfAscension.MOD_ID + ".imbuing_chamber");

	private final ImbuingChamberItemHandler itemHandler = new ImbuingChamberItemHandler();

	public ImbuingChamberBlockEntity(BlockPos pos, BlockState state) {
		super(AoABlockEntities.IMBUING_CHAMBER.get(), pos, state);
	}

	@Override
	public int getContainerSize() {
		return 7;
	}

	@Override
	public GenericItemStackHandler getItemHandler() {
		return this.itemHandler;
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return getItemHandler().getAllStacks();
	}

	@Override
	public Component getDefaultName() {
		return DEFAULT_NAME;
	}

	@Override
	public CompoundTag saveContents(HolderLookup.Provider holderLookup) {
		return getItemHandler().serializeNBT(holderLookup);
	}

	@Override
	public void loadContents(HolderLookup.Provider holderLookup, CompoundTag compound) {
		getItemHandler().deserializeNBT(holderLookup, compound);
	}

	@Override
	public void clearContent() {
		getItemHandler().clear();
	}

	@Override
	public void setItemNoUpdate(int slot, ItemStack stack) {
		getItemHandler().setStackInSlot(slot, stack);
	}

	@Override
	public ImbuingChamberMenu createMenu(int containerId, Inventory playerInventory) {
		ImbuingChamberMenu container = new ImbuingChamberMenu(containerId, playerInventory, ContainerLevelAccess.create(playerInventory.player.level(), getBlockPos())) {
			@Override
			protected void clearContainer(Player player, Container container) {
				setItems(getInventory().getItems());
				getInventory().clearContent();

				super.clearContainer(player, container);
			}

			@Override
			public void slotsChanged(Container inventory) {
				if (playerInventory.player.containerMenu == this)
					setItems(getInventory().getItems());

				super.slotsChanged(inventory);
			}
		};

		final NonNullList<ItemStack> contents = getItems();

		for (int i = 0; i < 6 && i < contents.size(); i++) {
			container.getInventory().setItem(i, contents.get(i));
		}

		container.getOutputSlot().set(contents.get(6));

		return container;
	}

	public static class ImbuingChamberItemHandler extends GenericItemStackHandler {
		ImbuingChamberItemHandler() {
			super(7);
		}

		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			if (slot > 0 && slot < 6)
				return stack.getItem() instanceof AspectFocusItem;

			return true;
		}
	}
}
