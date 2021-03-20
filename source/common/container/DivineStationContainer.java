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
		this(id, inventory, IWorldPosCallable.NULL);
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
	public void slotsChanged(IInventory inventory) {
		functionCaller.execute((world, pos) -> slotChangedCraftingGrid(world, player, inputs, output));
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return stillValid(functionCaller, player, AoABlocks.DIVINE_STATION.get());
	}

	@Override
	public void removed(PlayerEntity player) {
		super.removed(player);

		functionCaller.execute((world, pos) -> clearContainer(player, world, inputs));
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != output && super.canTakeItemForPickAll(stack, slot);
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();

			if (index == 0) {
				functionCaller.execute((world, pos) -> slotStack.getItem().onCraftedBy(slotStack, player.level, player));

				if (!moveItemStackTo(slotStack, 3, 39, true))
					return ItemStack.EMPTY;

				slot.onQuickCraft(slotStack, stack);
			}
			else if (index > 2) {
				if (index < 39 && !moveItemStackTo(slotStack, 1, 3, false))
					return ItemStack.EMPTY;
			}
			else if (!moveItemStackTo(slotStack, 3, 39, false)) {
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

			if (index == 0)
				player.drop(slot.onTake(player, slotStack), false);
		}

		return stack;
	}

	protected void slotChangedCraftingGrid(World world, PlayerEntity player, DivineStationInventory inv, CraftResultInventory craftResult) {
		if (!world.isClientSide) {
			ItemStack resultStack = ItemStack.EMPTY;
			Optional<UpgradeKitRecipe> recipeMatch = world.getServer().getRecipeManager().getRecipeFor(AoARecipes.UPGRADE_KIT.getA(), inv, world);

			if (recipeMatch.isPresent()) {
				UpgradeKitRecipe recipe = recipeMatch.get();

				if (recipe.isSpecial() || !world.getGameRules().getBoolean(GameRules.RULE_LIMITED_CRAFTING) || ((ServerPlayerEntity)player).getRecipeBook().contains(recipe)) {
					craftResult.setRecipeUsed(recipe);

					resultStack = recipe.assemble(inv);
				}
			}

			craftResult.setItem(0, resultStack);
			((ServerPlayerEntity)player).connection.send(new SSetSlotPacket(this.containerId, 0, resultStack));
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
				return new DivineStationContainer(windowId, inv, IWorldPosCallable.create(player.level, pos));
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
		public int getContainerSize() {
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
		public ItemStack getItem(int index) {
			return index >= getContainerSize() || index < 0 ? ItemStack.EMPTY : stackList.get(index);
		}

		@Override
		public ItemStack removeItemNoUpdate(int index) {
			return ItemStackHelper.takeItem(stackList, index);
		}

		@Override
		public ItemStack removeItem(int index, int count) {
			ItemStack stack = ItemStackHelper.removeItem(stackList, index, count);

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
		public void fillStackedContents(RecipeItemHelper recipeItemHelper) {
			for (ItemStack stack : stackList) {
				recipeItemHelper.accountStack(stack);
			}
		}
	}
}
