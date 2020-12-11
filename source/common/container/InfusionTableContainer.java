package net.tslat.aoa3.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.container.recipe.InfusionRecipe;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoARecipes;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.Optional;

public class InfusionTableContainer extends Container {
	private final InfusionInventory inputs = new InfusionInventory(this);
	private final CraftResultInventory output = new CraftResultInventory();
	private final IWorldPosCallable functionCaller;
	private final PlayerEntity player;

	public InfusionTableContainer(int id, PlayerInventory plInventory, IWorldPosCallable functionCaller) {
		super(AoAContainers.INFUSION_TABLE.get(), id);

		this.functionCaller = functionCaller;
		this.player = plInventory.player;


		addSlot(new SlotCraftingMod(player, inputs, output, 0, 139, 35) {
			@Override
			protected void onCrafting(ItemStack stack) {
				if (!player.world.isRemote) {
					InfusionRecipe recipe = (InfusionRecipe)((CraftResultInventory)inventory).getRecipeUsed();

					if (recipe != null && recipe.getMaxXp() > 0) {
						if (recipe.getMinXp() == recipe.getMaxXp()) {
							applyRecipeXp((ServerPlayerEntity)player, recipe.getMinXp());
						}
						else {
							applyRecipeXp((ServerPlayerEntity)player, recipe.getMinXp() + player.getRNG().nextInt(recipe.getMaxXp() - recipe.getMinXp()));
						}
					}
				}

				super.onCrafting(stack);
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
	public void onCraftMatrixChanged(IInventory inventory) {
		functionCaller.consume((world, pos) -> slotChangedCraftingGrid(world, player, inputs, output));
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(functionCaller, player, AoABlocks.INFUSION_TABLE.get());
	}

	@Override
	public void onContainerClosed(PlayerEntity player) {
		super.onContainerClosed(player);

		functionCaller.consume((world, pos) -> clearContainer(player, world, inputs));
	}

	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return slot.inventory != output && super.canMergeSlot(stack, slot);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index == 0) {
				functionCaller.consume((world, pos) -> slotStack.getItem().onCreated(slotStack, player.world, player));

				if (!mergeItemStack(slotStack, 11, 47, true))
					return ItemStack.EMPTY;
			}
			else if (index >= 11 && index < 38) {
				if (!mergeItemStack(slotStack, 1, 11, false) && !mergeItemStack(slotStack, 38, 47, false))
					return ItemStack.EMPTY;
			}
			else if (index >= 38 && index < 47) {
				if (!mergeItemStack(slotStack, 1, 11, false) && !mergeItemStack(slotStack, 11, 38, false))
					return ItemStack.EMPTY;
			}
			else if (!mergeItemStack(slotStack, 11, 47, false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}

			if (slotStack.getCount() == stack.getCount())
				return ItemStack.EMPTY;

			if (index == 0)
				player.dropItem(slot.onTake(player, slotStack), false);
		}

		return stack;
	}

	public static void openContainer(ServerPlayerEntity player, BlockPos pos) {
		NetworkHooks.openGui(player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("container.aoa3.infusion_table");
			}

			@Nullable
			@Override
			public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
				return new InfusionTableContainer(windowId, inv, IWorldPosCallable.of(player.world, pos));
			}
		}, pos);
	}

	protected void slotChangedCraftingGrid(World world, PlayerEntity player, InfusionInventory inv, CraftResultInventory craftResult) {
		if (!world.isRemote) {
			ItemStack resultStack = ItemStack.EMPTY;
			Optional<InfusionRecipe> recipeMatch = world.getServer().getRecipeManager().getRecipe(AoARecipes.INFUSION.getA(), inv, world);

			if (recipeMatch.isPresent()) {
				InfusionRecipe matchedRecipe = recipeMatch.get();

				if ((matchedRecipe.isDynamic() || !world.getGameRules().getBoolean(GameRules.DO_LIMITED_CRAFTING) || ((ServerPlayerEntity)player).getRecipeBook().isUnlocked(matchedRecipe)) && (player.isCreative() || PlayerUtil.doesPlayerHaveLevel((ServerPlayerEntity)player, Skills.INFUSION, matchedRecipe.getInfusionReq()))) {
					craftResult.setRecipeUsed(matchedRecipe);

					resultStack = matchedRecipe.getCraftingResult(inv);
				}
			}

			craftResult.setInventorySlotContents(0, resultStack);
			((ServerPlayerEntity)player).connection.sendPacket(new SSetSlotPacket(this.windowId, 0, resultStack));
		}
	}

	private void applyRecipeXp(ServerPlayerEntity player, float xp) {
		PlayerUtil.giveXpToPlayer(player, Skills.INFUSION, xp);
	}

	public static class InfusionInventory extends CraftingInventory {
		private final NonNullList<ItemStack> stackList;
		private final Container eventListener;

		public InfusionInventory(Container eventHandler) {
			super(eventHandler, 0, 0);

			stackList = NonNullList.<ItemStack>withSize(10, ItemStack.EMPTY);
			eventListener = eventHandler;
		}

		@Override
		public int getSizeInventory() {
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
		public ItemStack getStackInSlot(int index) {
			return index >= getSizeInventory() || index < 0 ? ItemStack.EMPTY : stackList.get(index);
		}

		@Override
		public ItemStack removeStackFromSlot(int index) {
			return ItemStackHelper.getAndRemove(stackList, index);
		}

		@Override
		public ItemStack decrStackSize(int index, int count) {
			ItemStack stack = ItemStackHelper.getAndSplit(stackList, index, count);

			if (!stack.isEmpty())
				eventListener.onCraftMatrixChanged(this);

			return stack;
		}

		@Override
		public void setInventorySlotContents(int index, ItemStack stack) {
			stackList.set(index, stack);
			eventListener.onCraftMatrixChanged(this);
		}

		@Override
		public void clear() {
			stackList.clear();
		}

		@Override
		public void fillStackedContents(RecipeItemHelper recipeItemHelper) {
			for (ItemStack stack : stackList) {
				recipeItemHelper.accountStack(stack);
			}
		}
	}

	public static class SlotCraftingMod extends Slot {
		private final InfusionInventory craftInv;
		private final PlayerEntity player;
		private int amountCrafted;

		public SlotCraftingMod(PlayerEntity pl, InfusionInventory craftInv, CraftResultInventory inv, int slotIndex, int xPos, int yPos) {
			super(inv, slotIndex, xPos, yPos);

			this.player = pl;
			this.craftInv = craftInv;
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return false;
		}

		@Override
		public ItemStack decrStackSize(int amount) {
			if (getHasStack())
				amountCrafted += Math.min(amount, getStack().getCount());

			return super.decrStackSize(amount);
		}

		@Override
		protected void onCrafting(ItemStack stack, int amount) {
			amountCrafted += amount;

			onCrafting(stack);
		}

		@Override
		protected void onSwapCraft(int amount) {
			amountCrafted += amount;
		}

		@Override
		protected void onCrafting(ItemStack stack) {
			if (amountCrafted > 0) {
				stack.onCrafting(player.world, player, amountCrafted);
				BasicEventHooks.firePlayerCraftingEvent(player, stack, craftInv);
			}

			amountCrafted = 0;
			((CraftResultInventory)inventory).setRecipeUsed(null);
		}

		@Override
		public ItemStack onTake(PlayerEntity player, ItemStack stack) {
			onCrafting(stack);
			ForgeHooks.setCraftingPlayer(player);
			NonNullList<ItemStack> remainingItems = player.world.getRecipeManager().getRecipeNonNull(AoARecipes.INFUSION.getA(), craftInv, player.world);
			ForgeHooks.setCraftingPlayer(null);

			for (int i = 0; i < remainingItems.size(); ++i) {
				ItemStack slotStack = this.craftInv.getStackInSlot(i);
				ItemStack remainingItem = remainingItems.get(i);

				if (!slotStack.isEmpty()) {
					craftInv.decrStackSize(i, 1);
					slotStack = craftInv.getStackInSlot(i);
				}

				if (!remainingItem.isEmpty()) {
					if (slotStack.isEmpty()) {
						craftInv.setInventorySlotContents(i, remainingItem);
					}
					else if (ItemStack.areItemsEqual(slotStack, remainingItem) && ItemStack.areItemStackTagsEqual(slotStack, remainingItem)) {
						remainingItem.grow(slotStack.getCount());
						craftInv.setInventorySlotContents(i, remainingItem);
					}
					else if (!player.inventory.addItemStackToInventory(remainingItem)) {
						player.dropItem(remainingItem, false);
					}
				}
			}

			return stack;
		}
	}
}