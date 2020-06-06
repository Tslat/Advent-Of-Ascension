package net.tslat.aoa3.common.containers;

import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.crafting.recipes.UpgradeKitRecipe;
import net.tslat.aoa3.utils.StringUtil;

public class ContainerDivineStation extends Container {
	public InventoryDivineStation inputs = new InventoryDivineStation(this);
	public InventoryCraftResult output = new InventoryCraftResult();

	private final World world;
	private final BlockPos pos;
	private final EntityPlayer player;

	public ContainerDivineStation(EntityPlayer player, World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
		this.player = player;

		addSlotToContainer(new SlotCrafting(player, inputs, output, 0, 134, 23));
		addSlotToContainer(new Slot(inputs, 0, 27, 23));
		addSlotToContainer(new Slot(inputs, 1, 76, 23));

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlotToContainer(new Slot(player.inventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 60 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlotToContainer(new Slot(player.inventory, hotbarSlot, 8 + hotbarSlot * 18, 118));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		super.onCraftMatrixChanged(inventory);

		if (inventory == inputs)
			slotChangedCraftingGrid(world, player, inputs, output);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		if (world.getBlockState(pos).getBlock() != BlockRegister.DIVINE_STATION)
			return false;

		return player.getDistanceSq(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d) <= 64;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!world.isRemote)
			clearContainer(player, world, inputs);
	}

	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return slot.inventory != output && super.canMergeSlot(stack, slot);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index == 0) {
				slotStack.getItem().onCreated(slotStack, world, player);

				if (!mergeItemStack(slotStack, 3, 39, true))
					return ItemStack.EMPTY;

				slot.onSlotChange(slotStack, stack);
			}
			else if (index != 1) {
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

	@Override
	protected void slotChangedCraftingGrid(World world, EntityPlayer player, InventoryCrafting inv, InventoryCraftResult craftResult) {
		if (!world.isRemote) {
			ItemStack resultStack = ItemStack.EMPTY;
			IRecipe matchedRecipe = CraftingManager.findMatchingRecipe(inv, world);

			if (matchedRecipe instanceof UpgradeKitRecipe) {
				if (matchedRecipe.isDynamic() || !world.getGameRules().getBoolean("doLimitedCrafting") || ((EntityPlayerMP)player).getRecipeBook().isUnlocked(matchedRecipe)) {
					craftResult.setRecipeUsed(matchedRecipe);

					resultStack = matchedRecipe.getCraftingResult(inv);
				}
			}

			craftResult.setInventorySlotContents(0, resultStack);
			((EntityPlayerMP)player).connection.sendPacket(new SPacketSetSlot(this.windowId, 0, resultStack));
		}
	}

	public static class InventoryDivineStation extends InventoryCrafting {
		private final NonNullList<ItemStack> stackList;
		private final Container eventListener;

		public InventoryDivineStation(Container container) {
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
		public ItemStack getStackInRowAndColumn(int row, int column) {
			return row == 1 && column >= 0 && column <= 1 ? getStackInSlot(column) : ItemStack.EMPTY;
		}

		@Override
		public String getName() {
			return "container.aoa3.divineStation";
		}

		@Override
		public boolean hasCustomName() {
			return false;
		}

		@Override
		public ITextComponent getDisplayName() {
			return StringUtil.getLocale(getName());
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
