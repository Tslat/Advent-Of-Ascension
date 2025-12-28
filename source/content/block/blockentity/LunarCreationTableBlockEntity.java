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
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.library.object.extension.GenericItemStackHandler;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

public class LunarCreationTableBlockEntity extends GenericContainerBlockEntity {
	private static final Component DEFAULT_NAME = Component.translatable(LocaleUtil.createContainerLocaleKey("lunar_creation_table"));

	private final GenericItemStackHandler itemHandler = new GenericItemStackHandler(9);

	public LunarCreationTableBlockEntity(BlockPos pos, BlockState state) {
		super(AoABlockEntities.LUNAR_CREATION_TABLE.get(), pos, state);
	}

	@Override
	public int getContainerSize() {
		return 9;
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

	@Nullable
	@Override
	public CraftingMenu createMenu(int containerId, Inventory playerInventory) {
		CraftingMenu container = new CraftingMenu(containerId, playerInventory, ContainerLevelAccess.create(getLevel(), getBlockPos())) {
			@Override
			protected void clearContainer(Player player, Container container) {
				setItems(NonNullList.copyOf(this.craftSlots.getItems()));

				this.craftSlots.clearContent();

				super.clearContainer(player, container);
			}

			@Override
			public void slotsChanged(Container inventory) {
				if (playerInventory.player.containerMenu == this)
					setItems(NonNullList.copyOf(this.craftSlots.getItems()));

				super.slotsChanged(inventory);
			}

			@Override
			public boolean stillValid(Player player) {
				return stillValid(this.access, player, AoABlocks.LUNAR_CREATION_TABLE.get());
			}
		};

		NonNullList<ItemStack> contents = getItems();

		for (int i = 0; i < contents.size(); i++) {
			container.craftSlots.setItem(i, contents.get(i));
		}

		return container;
	}
}
