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
import net.tslat.aoa3.common.menu.InfusedPressMenu;
import net.tslat.aoa3.common.menu.generic.GenericRecipeInput;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlockEntities;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.library.object.extension.GenericItemStackHandler;
import net.tslat.aoa3.util.LocaleUtil;

public class InfusedPressBlockEntity extends GenericContainerBlockEntity {
	private static final Component DEFAULT_NAME = Component.translatable(LocaleUtil.createContainerLocaleKey("infused_press"));

	private final InfusedPressItemHandler itemHandler = new InfusedPressItemHandler();

	public InfusedPressBlockEntity(BlockPos pos, BlockState state) {
		super(AoABlockEntities.INFUSED_PRESS.get(), pos, state);
	}

	@Override
	public int getContainerSize() {
		return 10;
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

	public void setOutput(ItemStack itemStack) {
		setItem(9, itemStack);
	}

	@Override
	public void setItemNoUpdate(int slot, ItemStack stack) {
		getItemHandler().setStackInSlot(slot, stack);
	}

	public boolean compress() {
		GenericRecipeInput inputs = new GenericRecipeInput(getItemHandler().getAllStacks());

		return getLevel().getRecipeManager().getRecipeFor(AoARecipes.INFUSED_PRESS_COMPRESSION.type().get(), inputs, level).map(recipe -> {
			ItemStack result = recipe.value().assemble(inputs, level.registryAccess());

			if (!result.isEmpty()) {
				getItemHandler().setStackInSlot(9, result);

				for (int i = 0; i < 9; i++) {
					getItemHandler().setStackInSlot(i, ItemStack.EMPTY);
				}

				markUpdated();

				return true;
			}

			return false;
		}).orElse(false);
	}

	public boolean decompress() {
		GenericRecipeInput inputs = new GenericRecipeInput(getItemHandler().getAllStacks());

		return getLevel().getRecipeManager().getRecipeFor(AoARecipes.INFUSED_PRESS_DECOMPRESSION.type().get(), inputs, level).map(recipe -> {
			ItemStack result = recipe.value().assemble(inputs, level.registryAccess());

			if (!result.isEmpty()) {
				getItemHandler().setStackInSlot(9, ItemStack.EMPTY);

				for (int i = 0; i < 9; i++) {
					getItemHandler().setStackInSlot(i, result.copy());
				}

				markUpdated();

				return true;
			}

			return false;
		}).orElse(false);
	}

	@Override
	public InfusedPressMenu createMenu(int containerId, Inventory playerInventory) {
		InfusedPressMenu container = new InfusedPressMenu(containerId, playerInventory, ContainerLevelAccess.create(getLevel(), getBlockPos())) {
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

		for (int i = 0; i < getItemHandler().getSlots(); i++) {
			container.getInventory().setItem(i, getItemHandler().getStackInSlot(i));
		}

		return container;
	}

	public static class InfusedPressItemHandler extends GenericItemStackHandler {
		InfusedPressItemHandler() {
			super(10);
		}

		@Override
		public int getSlotLimit(int slot) {
			if (slot == 9)
				return 1;

			return super.getSlotLimit(slot);
		}

		@Override
		public boolean isItemValid(int slot, ItemStack stack) {
			if (slot == 9)
				return stack.is(AoAItems.COMPRESSED_ITEM);

			return stack.is(AoAItems.COMPRESSED_ITEM) || stack.isStackable();
		}
	}
}
