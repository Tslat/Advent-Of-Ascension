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
import net.tslat.aoa3.crafting.recipes.InfusionTableRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class ContainerInfusionTable extends Container {
	private InventoryInfusion craftInputs = new InventoryInfusion(this);
	private InventoryCraftResult output = new InventoryCraftResult();

	private final World world;
	private final BlockPos pos;
	private final EntityPlayer player;

	public ContainerInfusionTable(EntityPlayer player, World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
		this.player = player;

		addSlotToContainer(new SlotCrafting(player, craftInputs, output, 0, 139, 35) {
			@Override
			protected void onCrafting(ItemStack stack) {
				if (!world.isRemote) {
					InfusionTableRecipe recipe = (InfusionTableRecipe)((InventoryCraftResult)inventory).getRecipeUsed();

					if (recipe != null && recipe.getMaxXp() > 0) {
						if (recipe.getMinXp() == recipe.getMaxXp()) {
							applyRecipeXp(player, recipe.getMinXp());
						}
						else {
							applyRecipeXp(player, recipe.getMinXp() + player.getRNG().nextInt(recipe.getMaxXp() - recipe.getMinXp()));
						}
					}
				}

				super.onCrafting(stack);
			}
		});

		addSlotToContainer(new Slot(craftInputs, 0, 17, 35));

		for (int matrixY = 0; matrixY < 3; matrixY++) {
			for (int matrixX = 0; matrixX < 3; matrixX++) {
				addSlotToContainer(new Slot(craftInputs, 1 + matrixX + matrixY * 3, 45 + matrixX * 18, 17 + matrixY * 18));
			}
		}

		for (int inventoryY = 0; inventoryY < 3; inventoryY++) {
			for (int inventoryX = 0; inventoryX < 9; inventoryX++) {
				addSlotToContainer(new Slot(player.inventory, inventoryX + inventoryY * 9 + 9, 8 + inventoryX * 18, 84 + inventoryY * 18));
			}
		}

		for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++) {
			addSlotToContainer(new Slot(player.inventory, hotbarSlot, 8 + hotbarSlot * 18, 142));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		super.onCraftMatrixChanged(inventory);

		if (inventory == craftInputs)
			slotChangedCraftingGrid(world, player, craftInputs, output);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (world.getBlockState(pos).getBlock() != BlockRegister.infusionTable)
			return false;

		return player.getDistanceSq(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d) <= 64;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!world.isRemote)
			clearContainer(player, world, craftInputs);
	}

	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slot) {
		return slot.inventory != output && super.canMergeSlot(stack, slot);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (index == 0) {
				slotStack.getItem().onCreated(slotStack, world, player);

				if (!mergeItemStack(slotStack, 11, 47, true))
					return ItemStack.EMPTY;
			}
			else if (index >= 11 && index < 38) {
				if (!mergeItemStack(slotStack, 38, 47, false))
					return ItemStack.EMPTY;
			}
			else if (index >= 38 && index < 47) {
				if (!mergeItemStack(slotStack, 11, 38, false))
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

	@Override
	protected void slotChangedCraftingGrid(World world, EntityPlayer player, InventoryCrafting inv, InventoryCraftResult craftResult) {
		if (!world.isRemote) {
			ItemStack resultStack = ItemStack.EMPTY;
			IRecipe matchingRecipe = CraftingManager.findMatchingRecipe(inv, world);

			if (matchingRecipe instanceof InfusionTableRecipe) {
				InfusionTableRecipe matchedRecipe = (InfusionTableRecipe)matchingRecipe;

				if ((matchedRecipe.isDynamic() || !world.getGameRules().getBoolean("doLimitedCrafting") || ((EntityPlayerMP)player).getRecipeBook().isUnlocked(matchedRecipe)) && (player.isCreative() || PlayerUtil.doesPlayerHaveLevel(player, Enums.Skills.INFUSION, matchedRecipe.getInfusionReq()))) {
					craftResult.setRecipeUsed(matchedRecipe);

					resultStack = matchedRecipe.getCraftingResult(inv);
				}
			}

			craftResult.setInventorySlotContents(0, resultStack);
			((EntityPlayerMP)player).connection.sendPacket(new SPacketSetSlot(this.windowId, 0, resultStack));
		}
	}

	private void applyRecipeXp(EntityPlayer player, float xp) {
		PlayerUtil.giveXpToPlayer(player, Enums.Skills.INFUSION, xp);
	}

	public static class InventoryInfusion extends InventoryCrafting {
		private final NonNullList<ItemStack> stackList;
		private final Container eventListener;

		public InventoryInfusion(Container eventHandler) {
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
		public ItemStack getStackInRowAndColumn(int row, int column) {
			return row >= 0 && row < 3 && column >= 0 && column <= 3 ? getStackInSlot(1 + row + column * 3) : ItemStack.EMPTY;
		}

		@Override
		public String getName() {
			return "container.aoa3.infusion";
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
