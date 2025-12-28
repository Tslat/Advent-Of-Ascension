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
import net.tslat.aoa3.common.menu.InfusionTableMenu;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.library.object.extension.GenericItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class InfusionTableBlockEntity extends GenericContainerBlockEntity {
	private static final Component DEFAULT_NAME = Component.translatable("container." + AdventOfAscension.MOD_ID + ".infusion_table");

	private final GenericItemStackHandler itemHandler = new GenericItemStackHandler(getContainerSize());

	public InfusionTableBlockEntity(BlockPos pos, BlockState state) {
		super(AoABlockEntities.INFUSION_TABLE.get(), pos, state);
	}

	@Override
	public int getContainerSize() {
		return 11;
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
		markUpdated();
	}

	public void setOutput(ItemStack itemStack) {
		setItem(10, itemStack);
	}

	@Override
	public void setItemNoUpdate(int slot, ItemStack stack) {
		getItemHandler().setStackInSlot(slot, stack);
	}

	@Nullable
	@Override
	public InfusionTableMenu createMenu(int containerId, Inventory playerInventory) {
		InfusionTableMenu container = new InfusionTableMenu(containerId, playerInventory, ContainerLevelAccess.create(getLevel(), getBlockPos())) {
			@Override
			protected void clearContainer(Player player, Container container) {
				setItems(getInventory().getItems());
				setOutput(getOutputItem());
				getInventory().clearContent();

				super.clearContainer(player, container);
			}

			@Override
			public void slotsChanged(Container inventory) {
				if (playerInventory.player.containerMenu == this) {
					setItems(getInventory().getItems());
					setOutput(getOutputItem());
				}

				super.slotsChanged(inventory);
			}
		};

		NonNullList<ItemStack> contents = getItems();

		for (int i = 0; i < 10 && i < contents.size(); i++) {
			container.getInventory().setItem(i, contents.get(i));
		}

		return container;
	}
}
