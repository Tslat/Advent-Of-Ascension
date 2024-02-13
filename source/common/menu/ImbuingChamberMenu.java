package net.tslat.aoa3.common.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.menu.container.ListenableResultContainer;
import net.tslat.aoa3.common.menu.generic.ExtensibleContainerMenu;
import net.tslat.aoa3.common.menu.slot.CraftableResultSlot;
import net.tslat.aoa3.common.menu.slot.PredicatedSlot;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.block.blockentity.ImbuingChamberBlockEntity;
import net.tslat.aoa3.content.item.misc.AspectFocusItem;
import net.tslat.aoa3.content.recipe.ImbuingRecipe;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.Optional;
import java.util.function.Predicate;

public class ImbuingChamberMenu extends ExtensibleContainerMenu<ImbuingChamberMenu.ImbuingInventory> {
	public ImbuingChamberMenu(int screenId, Inventory playerInventory, ContainerLevelAccess accessValidator) {
		super(AoAMenus.IMBUING_CHAMBER.get(), screenId, playerInventory, accessValidator);

		createPlayerInventory(playerInventory, 8, 84);
	}

	@Override
	public int inputSlotCount() {
		return 6;
	}

	@Override
	protected ImbuingChamberMenu.ImbuingInventory createInventory() {
		return new ImbuingInventory(this);
	}

	@Override
	protected Block getContainerBlock() {
		return AoABlocks.IMBUING_CHAMBER.get();
	}

	@Override
	protected Slot createInputSlot(int slotIndex, ImbuingChamberMenu.ImbuingInventory inventory) {
		if (slotIndex == 0)
			return new Slot(inventory, slotIndex, 17, 35);

		return new PredicatedSlot(inventory, slotIndex, 19 + 19 * slotIndex, 35, stack -> stack.getItem() instanceof AspectFocusItem);
	}

	@Override
	protected Slot createOutputSlot(int slotIndex, Player player) {
		return new ImbuingResultSlot(player, getInventory(), new ListenableResultContainer(stack -> getInventory().setItem(getOutputSlotIndex(), stack)), slotIndex, 139, 35);
	}

	@Override
	protected void handleContainerUpdate() {
		final ImbuingResultSlot outputSlot = (ImbuingResultSlot)getOutputSlot();
		final Player player = outputSlot.getPlayer();

		if (!getInventory().updatingOutput) {
			getInventory().updatingOutput = true;
			updateRecipeOutput(AoARecipes.IMBUING.type().get(), player, (ResultContainer)outputSlot.container, recipe -> canUseRecipe(player, recipe));
		}

		getInventory().updatingOutput = false;
	}

	@Override
	protected <R extends Recipe<ImbuingInventory>> void updateRecipeOutput(RecipeType<R> recipeType, Player player, ResultContainer resultContainer, Predicate<RecipeHolder<R>> recipePredicate) {
		final ImbuingInventory container = getInventory();
		final Optional<RecipeHolder<R>> recipeHolder = getOrFindRecipe(recipeType, container, resultContainer, player.level());

		if (player instanceof ServerPlayer serverPlayer) {
			final ServerLevel level = serverPlayer.serverLevel();
			final ItemStack outputStack = recipeHolder
					.filter(holder -> recipePredicate.test(holder) && resultContainer.setRecipeUsed(level, serverPlayer, holder))
					.map(RecipeHolder::value)
					.map(recipe -> recipe.assemble(container, level.registryAccess()))
					.filter(recipeResult -> recipeResult.isItemEnabled(level.enabledFeatures()))
					.filter(stack -> !AoAEvents.firePlayerCraftingEvent(player, stack, getInventory(), resultContainer))
					.orElse(getOutputItem());

			if (!ItemStack.isSameItemSameTags(getOutputItem(), outputStack)) {
				inventory.imbuing = true;

				setOutputItem(outputStack);
				setRemoteSlot(getOutputSlotIndex(), outputStack);
				serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(this.containerId, incrementStateId(), getOutputSlotIndex(), outputStack));
			}
		}
		else {
			resultContainer.setRecipeUsed(recipeHolder.orElse(null));
		}
	}

	private static boolean canUseRecipe(Player player, RecipeHolder<ImbuingRecipe> recipe) {
		return player.isCreative() || recipe.value().getImbuingLevelReq() <= 1 || PlayerUtil.doesPlayerHaveLevel(player, AoASkills.IMBUING.get(), recipe.value().getImbuingLevelReq());
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		final BlockEntity blockEntity = player.level().getBlockEntity(pos);

		if (!(blockEntity instanceof ImbuingChamberBlockEntity imbuingChamber))
			return;

		player.openMenu(imbuingChamber, pos);
	}

	public static class ImbuingInventory extends TransientCraftingContainer {
		public boolean imbuing = false;
		public boolean updatingOutput = false;

		public ImbuingInventory(ImbuingChamberMenu menu) {
			super(menu, 7, 1, NonNullList.withSize(7, ItemStack.EMPTY));
		}
	}

	public static class ImbuingResultSlot extends CraftableResultSlot<ImbuingInventory, ImbuingRecipe> {
		public ImbuingResultSlot(Player pl, ImbuingInventory craftInv, ResultContainer inv, int slotIndex, int xPos, int yPos) {
			super(pl, craftInv, inv, AoARecipes.IMBUING.type().get(), slotIndex, xPos, yPos);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return getCurrentRecipe().map(recipe -> recipe.canEnchantInput(stack)).orElse(false);
		}

		@Override
		protected void onQuickCraft(ItemStack stack, int amount) {
			this.removedCount += amount;
		}

		@Override
		public void set(ItemStack stack) {
			super.set(stack);

			if (!stack.isEmpty() && getInventoryContainer().imbuing) {
				awardForCrafting(stack, stack.getCount());
				getResultContainer().awardUsedRecipes(this.player, getContainerItems());
				EventHooks.firePlayerCraftingEvent(this.player, stack, this.container);
				onItemRemoved(this.player, stack);
			}
		}

		@Override
		public void onTake(Player player, ItemStack stack) {
			setChanged();
		}

		@Override
		protected NonNullList<ItemStack> getRemainingItems(RecipeType<ImbuingRecipe> recipeType, ImbuingInventory craftingContainer, Player player) {
			final NonNullList<ItemStack> remainingStacks = super.getRemainingItems(recipeType, craftingContainer, player);
			craftingContainer.imbuing = false;

			return remainingStacks;
		}

		@Override
		protected void awardForCrafting(ItemStack stack, int amount) {
			if (getPlayer() instanceof ServerPlayer pl) {
				getCurrentRecipe().ifPresent(recipe -> {
					final float xp = recipe.getXp(getPlayer());

					if (xp > 0)
						PlayerUtil.giveXpToPlayer(pl, AoASkills.IMBUING.get(), xp, false);
				});
			}
		}
	}
}