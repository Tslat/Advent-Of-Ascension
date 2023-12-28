package net.tslat.aoa3.common.container;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.recipe.InfusionRecipe;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class InfusionTableContainer extends AbstractContainerMenu {
	private final InfusionInventory inputs = new InfusionInventory(this);
	private final ResultContainer output = new ResultContainer();
	private final ContainerLevelAccess functionCaller;
	private final Player player;

	public InfusionTableContainer(int id, Inventory plInventory, ContainerLevelAccess functionCaller) {
		super(AoAContainers.INFUSION_TABLE.get(), id);

		this.functionCaller = functionCaller;
		this.player = plInventory.player;


		addSlot(new SlotCraftingMod(player, inputs, output, 0, 139, 35) {
			@Override
			protected void checkTakeAchievements(ItemStack stack) {
				if (!player.level().isClientSide) {
					InfusionRecipe recipe = (InfusionRecipe)((ResultContainer)container).getRecipeUsed();

					if (recipe != null && recipe.getMaxXp() > 0) {
						if (recipe.getMinXp() == recipe.getMaxXp()) {
							applyRecipeXp((ServerPlayer)player, recipe.getMinXp());
						}
						else {
							applyRecipeXp((ServerPlayer)player, recipe.getMinXp() + player.getRandom().nextInt(recipe.getMaxXp() - recipe.getMinXp()));
						}
					}
				}

				super.checkTakeAchievements(stack);
			}
		});

		addSlot(new Slot(inputs, 0, 17, 35));

		for (int matrixY = 0; matrixY < 3; matrixY++) {
			for (int matrixX = 0; matrixX < 3; matrixX++) {
				addSlot(new Slot(inputs, 1 + matrixX + matrixY * 3, 45 + matrixX * 18, 17 + matrixY * 18));
			}
		}

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlot(new Slot(plInventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 84 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(plInventory, hotbarSlot, 8 + hotbarSlot * 18, 142));
		}
	}

	@Override
	public void slotsChanged(Container inventory) {
		functionCaller.execute((world, pos) -> slotChangedCraftingGrid(world, player, inputs, output));
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(functionCaller, player, AoABlocks.INFUSION_TABLE.get());
	}

	@Override
	public void removed(Player player) {
		super.removed(player);

		functionCaller.execute((world, pos) -> clearContainer(player, inputs));
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != output && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index == 0) {
				functionCaller.execute((world, pos) -> slotStack.getItem().onCraftedBy(slotStack, player.level(), player));

				if (!moveItemStackTo(slotStack, 11, 47, true))
					return ItemStack.EMPTY;

				slot.onQuickCraft(slotStack, stack);
			}
			else if (index >= 11 && index < 38) {
				if (!moveItemStackTo(slotStack, 1, 11, false) && !moveItemStackTo(slotStack, 38, 47, false))
					return ItemStack.EMPTY;
			}
			else if (index >= 38 && index < 47) {
				if (!moveItemStackTo(slotStack, 1, 11, false) && !moveItemStackTo(slotStack, 11, 38, false))
					return ItemStack.EMPTY;
			}
			else if (!moveItemStackTo(slotStack, 11, 47, false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}
			else {
				slot.setChanged();
			}

			if (slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;

			slot.onTake(player, slotStack);

			if (index == 0)
				player.drop(slotStack, false);
		}

		return stack;
	}

	public static void openContainer(ServerPlayer player, BlockPos pos) {
		NetworkHooks.openScreen(player, new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return Component.translatable("container.aoa3.infusion_table");
			}

			@Nullable
			@Override
			public AbstractContainerMenu createMenu(int windowId, Inventory inv, Player player) {
				return new InfusionTableContainer(windowId, inv, ContainerLevelAccess.create(player.level(), pos));
			}
		}, pos);
	}

	protected void slotChangedCraftingGrid(Level world, Player player, InfusionInventory inv, ResultContainer craftResult) {
		if (!world.isClientSide) {
			ItemStack resultStack = ItemStack.EMPTY;
			Optional<InfusionRecipe> recipeMatch = world.getServer().getRecipeManager().getRecipeFor(AoARecipes.INFUSION.type().get(), inv, world);

			if (recipeMatch.isPresent()) {
				InfusionRecipe matchedRecipe = recipeMatch.get();

				if ((matchedRecipe.isSpecial() || !world.getGameRules().getBoolean(GameRules.RULE_LIMITED_CRAFTING) || ((ServerPlayer)player).getRecipeBook().contains(matchedRecipe)) && (player.isCreative() || PlayerUtil.doesPlayerHaveLevel((ServerPlayer)player, AoASkills.IMBUING.get(), matchedRecipe.getInfusionReq()))) {
					craftResult.setRecipeUsed(matchedRecipe);

					resultStack = matchedRecipe.assemble(inv, world.registryAccess());
				}
			}

			craftResult.setItem(0, resultStack);

			if (AoAEvents.firePlayerCraftingEvent(player, craftResult.getItem(0), inv, craftResult))
				craftResult.setItem(0, ItemStack.EMPTY);

			((ServerPlayer)player).connection.send(new ClientboundContainerSetSlotPacket(this.containerId, incrementStateId(), 0, resultStack));
		}
	}

	private void applyRecipeXp(ServerPlayer player, float xp) {
		PlayerUtil.giveXpToPlayer(player, AoASkills.IMBUING.get(), xp, false);
	}

	public static class InfusionInventory extends TransientCraftingContainer {
		private final NonNullList<ItemStack> stackList;
		private final AbstractContainerMenu eventListener;

		public InfusionInventory(AbstractContainerMenu eventHandler) {
			super(eventHandler, 0, 0);

			stackList = NonNullList.<ItemStack>withSize(10, ItemStack.EMPTY);
			eventListener = eventHandler;
		}

		@Override
		public int getContainerSize() {
			return 10;
		}

		@Override
		public boolean isEmpty() {
			for (ItemStack stack : stackList) {
				if (!stack.isEmpty())
					return false;
			}

			return true;
		}

		@Override
		public ItemStack getItem(int index) {
			return index >= getContainerSize() || index < 0 ? ItemStack.EMPTY : stackList.get(index);
		}

		@Override
		public ItemStack removeItemNoUpdate(int index) {
			return ContainerHelper.takeItem(stackList, index);
		}

		@Override
		public ItemStack removeItem(int index, int count) {
			ItemStack stack = ContainerHelper.removeItem(stackList, index, count);

			if (!stack.isEmpty())
				eventListener.slotsChanged(this);

			return stack;
		}

		@Override
		public void setItem(int index, ItemStack stack) {
			stackList.set(index, stack);
			eventListener.slotsChanged(this);
		}

		@Override
		public void clearContent() {
			stackList.clear();
		}

		@Override
		public void fillStackedContents(StackedContents recipeItemHelper) {
			for (ItemStack stack : stackList) {
				recipeItemHelper.accountStack(stack);
			}
		}
	}

	public static class SlotCraftingMod extends Slot {
		private final InfusionInventory craftInv;
		private final Player player;
		private int amountCrafted;

		public SlotCraftingMod(Player pl, InfusionInventory craftInv, ResultContainer inv, int slotIndex, int xPos, int yPos) {
			super(inv, slotIndex, xPos, yPos);

			this.player = pl;
			this.craftInv = craftInv;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public ItemStack remove(int amount) {
			if (hasItem())
				amountCrafted += Math.min(amount, getItem().getCount());

			return super.remove(amount);
		}

		@Override
		protected void onQuickCraft(ItemStack stack, int amount) {
			amountCrafted += amount;

			checkTakeAchievements(stack);
		}

		@Override
		protected void onSwapCraft(int amount) {
			amountCrafted += amount;
		}

		@Override
		protected void checkTakeAchievements(ItemStack stack) {
			if (amountCrafted > 0) {
				stack.onCraftedBy(player.level(), player, amountCrafted);
				ForgeEventFactory.firePlayerCraftingEvent(player, stack, craftInv);
			}

			amountCrafted = 0;
			((ResultContainer)container).setRecipeUsed(null);
		}

		@Override
		public void onTake(Player player, ItemStack stack) {
			checkTakeAchievements(stack);
			ForgeHooks.setCraftingPlayer(player);
			NonNullList<ItemStack> remainingItems = player.level().getRecipeManager().getRemainingItemsFor(AoARecipes.INFUSION.type().get(), craftInv, player.level());
			ForgeHooks.setCraftingPlayer(null);

			for (int i = 0; i < remainingItems.size(); ++i) {
				ItemStack slotStack = this.craftInv.getItem(i);
				ItemStack remainingItem = remainingItems.get(i);

				if (!slotStack.isEmpty()) {
					craftInv.removeItem(i, 1);
					slotStack = craftInv.getItem(i);
				}

				if (!remainingItem.isEmpty()) {
					if (slotStack.isEmpty()) {
						craftInv.setItem(i, remainingItem);
					}
					else if (ItemUtil.areStacksFunctionallyEqual(slotStack, remainingItem) && Objects.equals(slotStack.getTag(), remainingItem.getTag())) {
						remainingItem.grow(slotStack.getCount());
						craftInv.setItem(i, remainingItem);
					}
					else if (!player.getInventory().add(remainingItem)) {
						player.drop(remainingItem, false);
					}
				}
			}
		}
	}
}