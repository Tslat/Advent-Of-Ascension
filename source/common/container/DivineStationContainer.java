package net.tslat.aoa3.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
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
import net.minecraftforge.fml.network.NetworkHooks;
import net.tslat.aoa3.common.container.recipe.UpgradeKitRecipe;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAContainers;
import net.tslat.aoa3.common.registration.AoARecipes;

import javax.annotation.Nullable;
import java.util.Optional;

public class DivineStationContainer extends Container { // TODO Look at RecipeBookContainer extensions
	public DivineStationInventory inputs = new DivineStationInventory(this);
	public CraftResultInventory output = new CraftResultInventory();
	private final IWorldPosCallable functionCaller;
	private final PlayerEntity player;

	public DivineStationContainer(int id, PlayerInventory inventory) {
		this(id, inventory, IWorldPosCallable.DUMMY);
	}

	public DivineStationContainer(int screenId, PlayerInventory plInventory, IWorldPosCallable functionCaller) {
		super(AoAContainers.DIVINE_STATION.get(), screenId);

		this.functionCaller = functionCaller;
		this.player = plInventory.player;

		addSlot(new CraftingResultSlot(player, inputs, output, 0, 134, 23));
		addSlot(new Slot(inputs, 0, 27, 23));
		addSlot(new Slot(inputs, 1, 76, 23));

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlot(new Slot(plInventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 60 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlot(new Slot(plInventory, hotbarSlot, 8 + hotbarSlot * 18, 118));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		functionCaller.consume((world, pos) -> slotChangedCraftingGrid(world, player, inputs, output));
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(functionCaller, player, AoABlocks.DIVINE_STATION.get());
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

				if (!mergeItemStack(slotStack, 3, 39, true))
					return ItemStack.EMPTY;

				slot.onSlotChange(slotStack, stack);
			}
			else if (index > 2) {
				if (index < 39 && !mergeItemStack(slotStack, 1, 3, false))
					return ItemStack.EMPTY;
			}
			else if (!mergeItemStack(slotStack, 3, 39, false)) {
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

	protected void slotChangedCraftingGrid(World world, PlayerEntity player, DivineStationInventory inv, CraftResultInventory craftResult) {
		if (!world.isRemote) {
			ItemStack resultStack = ItemStack.EMPTY;
			Optional<UpgradeKitRecipe> recipeMatch = world.getServer().getRecipeManager().getRecipe(AoARecipes.UPGRADE_KIT.getA(), inv, world);

			if (recipeMatch.isPresent()) {
				UpgradeKitRecipe recipe = recipeMatch.get();

				if (recipe.isDynamic() || !world.getGameRules().getBoolean(GameRules.DO_LIMITED_CRAFTING) || ((ServerPlayerEntity)player).getRecipeBook().isUnlocked(recipe)) {
					craftResult.setRecipeUsed(recipe);

					resultStack = recipe.getCraftingResult(inv);
				}
			}

			craftResult.setInventorySlotContents(0, resultStack);
			((ServerPlayerEntity)player).connection.sendPacket(new SSetSlotPacket(this.windowId, 0, resultStack));
		}
	}

	public static void openContainer(ServerPlayerEntity player, BlockPos pos) {
		NetworkHooks.openGui(player, new INamedContainerProvider() {
			@Override
			public ITextComponent getDisplayName() {
				return new TranslationTextComponent("container.aoa3.divine_station");
			}

			@Nullable
			@Override
			public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player) {
				return new DivineStationContainer(windowId, inv, IWorldPosCallable.of(player.world, pos));
			}
		}, pos);
	}

	public static class DivineStationInventory extends CraftingInventory {
		private final NonNullList<ItemStack> stackList;
		private final Container eventListener;

		public DivineStationInventory(Container container) {
			super(container, 0, 0);

			this.stackList = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
			this.eventListener = container;
		}

		@Override
		public int getSizeInventory() {
			return 2;
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
}
