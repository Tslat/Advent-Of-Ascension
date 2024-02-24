package net.tslat.aoa3.common.menu.generic;

import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.tslat.aoa3.event.custom.AoAEvents;

import java.util.Optional;
import java.util.function.Predicate;

public abstract class ExtensibleContainerMenu<I extends Container> extends AbstractContainerMenu {
	public final I inventory;
	protected final ContainerLevelAccess accessValidator;

	public ExtensibleContainerMenu(MenuType<?> type, int containerId, Inventory playerInventory, ContainerLevelAccess accessValidator) {
		super(type, containerId);

		this.accessValidator = accessValidator;
		this.inventory = createInventory();
		final int inputSlots = inputSlotCount();

		for (int i = 0; i < inputSlots; i++) {
			addSlot(createInputSlot(i, this.inventory));
		}

		addSlot(createOutputSlot(0, playerInventory.player));
	}

	public abstract int inputSlotCount();
	protected abstract I createInventory();
	protected abstract Slot createInputSlot(int slotIndex, I inventory);
	protected abstract Slot createOutputSlot(int slotIndex, Player player);
	protected abstract Block getContainerBlock();
	protected abstract void handleContainerUpdate();

	public int getOutputSlotIndex() {
		return inputSlotCount();
	}

	public Slot getOutputSlot() {
		return getSlot(getOutputSlotIndex());
	}

	public I getInventory() {
		return this.inventory;
	}

	public ItemStack getInputItem(int slotIndex) {
		return this.inventory.getItem(slotIndex);
	}

	public ItemStack getOutputItem() {
		return getOutputSlot().getItem();
	}

	public void setInputItem(int slotIndex, ItemStack stack) {
		this.inventory.setItem(slotIndex, stack);
	}

	public void consumeInputItem(int slotIndex, int amount) {
		this.inventory.removeItem(slotIndex, amount);
	}

	public void setOutputItem(ItemStack stack) {
		getOutputSlot().set(stack);
	}

	protected void handleContainerClosed(Player player) {
		clearContainer(player, this.inventory);
	}

	@Override
	public void slotsChanged(Container inventory) {
		this.accessValidator.execute((level, pos) -> {
			handleContainerUpdate();
			broadcastChanges();
		});
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot != getOutputSlot();
	}

	@Override
	public void removed(Player player) {
		super.removed(player);

		this.accessValidator.execute((world, pos) -> handleContainerClosed(player));
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.accessValidator, player, getContainerBlock());
	}

	protected void createPlayerInventory(Inventory playerInventory, final int baseX, final int baseY) {
		for (int invRow = 0; invRow < 3; invRow++) {
			for (int invCol = 0; invCol < 9; invCol++) {
				addSlot(new Slot(playerInventory, invCol + invRow * 9 + 9, baseX + invCol * 18, baseY + invRow * 18));
			}
		}

		for (int hotBarSlot = 0; hotBarSlot < 9; hotBarSlot++) {
			addSlot(new Slot(playerInventory, hotBarSlot, baseX + hotBarSlot * 18, baseY + 58));
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		final Slot slot = this.slots.get(slotIndex);

		if (!slot.hasItem())
			return ItemStack.EMPTY;

		final ItemStack slotStack = slot.getItem();
		final ItemStack newStack = slotStack.copy();
		final int outputSlot = getOutputSlotIndex();
		final int inputSlotsEnd = inputSlotCount();
		final int nonContainerSlotsStart = outputSlot + 1;
		final int lastSlot = this.slots.size();

		if (slotIndex == outputSlot) {
			this.accessValidator.execute((level, pos) -> slotStack.getItem().onCraftedBy(slotStack, player.level(), player));

			if (!moveItemStackTo(slotStack, nonContainerSlotsStart, lastSlot, true))
				return ItemStack.EMPTY;

			slot.onQuickCraft(slotStack, newStack);
		}
		else if (slotIndex > outputSlot) {
			if (slotIndex < lastSlot && !moveItemStackTo(slotStack, 0, inputSlotsEnd, false))
				return ItemStack.EMPTY;
		}
		else if (!moveItemStackTo(slotStack, nonContainerSlotsStart, lastSlot, false)) {
			return ItemStack.EMPTY;
		}

		if (slotStack.isEmpty()) {
			slot.set(ItemStack.EMPTY);
		}
		else {
			slot.setChanged();
		}

		if (slotStack.getCount() == newStack.getCount())
			return ItemStack.EMPTY;

		slot.onTake(player, slotStack);

		if (slotIndex == outputSlot)
			player.drop(slotStack, false);

		return newStack;
	}

	protected int gridXFromIndex(int index) {
		return (index % 3);
	}

	protected int gridYFromIndex(int index) {
		return index / 3;
	}

	protected <R extends Recipe<I>> void updateRecipeOutput(RecipeType<R> recipeType, Player player, ResultContainer resultContainer, Predicate<RecipeHolder<R>> recipePredicate) {
		if (player instanceof ServerPlayer serverPlayer) {
			final I container = getInventory();
			final ServerLevel level = serverPlayer.serverLevel();
			final ItemStack outputStack = getOrFindRecipe(recipeType, container, resultContainer, level)
					.filter(recipeHolder -> recipePredicate.test(recipeHolder) && resultContainer.setRecipeUsed(level, serverPlayer, recipeHolder))
					.map(RecipeHolder::value)
					.map(recipe -> recipe.assemble(container, level.registryAccess()))
					.filter(recipeResult -> recipeResult.isItemEnabled(level.enabledFeatures()))
					.filter(stack -> !AoAEvents.firePlayerCraftingEvent(player, stack, getInventory(), resultContainer))
					.orElse(ItemStack.EMPTY);

			if (!ItemStack.isSameItemSameTags(getOutputItem(), outputStack)) {
				setOutputItem(outputStack);
				setRemoteSlot(getOutputSlotIndex(), outputStack);
				serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(this.containerId, incrementStateId(), getOutputSlotIndex(), outputStack));
			}
		}
	}

	protected <R extends Recipe<I>> Optional<RecipeHolder<R>> getOrFindRecipe(RecipeType<R> recipeType, I container, ResultContainer resultContainer, Level level) {
		return Optional.ofNullable((RecipeHolder<R>)resultContainer.getRecipeUsed())
				.filter(holder -> holder.value().matches(container, level))
				.or(() -> level.getRecipeManager().getRecipeFor(recipeType, container, level));
	}
}
