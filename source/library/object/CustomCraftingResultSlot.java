package net.tslat.aoa3.library.object;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;

public class CustomCraftingResultSlot<C extends IInventory, T extends IRecipe<C>> extends Slot {
	private final C craftSlots;
	private final PlayerEntity player;
	private final IRecipeType<T> recipeType;
	private int removeCount;

	public CustomCraftingResultSlot(PlayerEntity player, C craftSlots, IInventory container, IRecipeType<T> recipeType, int slot, int posX, int posY) {
		super(container, slot, posX, posY);

		this.player = player;
		this.craftSlots = craftSlots;
		this.recipeType = recipeType;
	}

	public boolean mayPlace(ItemStack pStack) {
		return false;
	}

	public ItemStack remove(int amount) {
		if (hasItem())
			this.removeCount += Math.min(amount, getItem().getCount());

		return super.remove(amount);
	}

	protected void onQuickCraft(ItemStack stack, int amount) {
		this.removeCount += amount;

		checkTakeAchievements(stack);
	}

	protected void onSwapCraft(int craftCount) {
		this.removeCount += craftCount;
	}

	protected void checkTakeAchievements(ItemStack stack) {
		if (this.removeCount > 0) {
			stack.onCraftedBy(this.player.level, this.player, this.removeCount);
			BasicEventHooks.firePlayerCraftingEvent(this.player, stack, this.craftSlots);
		}

		if (this.container instanceof IRecipeHolder)
			((IRecipeHolder)this.container).awardUsedRecipes(this.player);

		this.removeCount = 0;
	}

	public ItemStack onTake(PlayerEntity player, ItemStack stack) {
		checkTakeAchievements(stack);
		ForgeHooks.setCraftingPlayer(player);

		NonNullList<ItemStack> remainingItems = player.level.getRecipeManager().getRemainingItemsFor(recipeType, this.craftSlots, player.level);

		ForgeHooks.setCraftingPlayer(null);

		for(int i = 0; i < remainingItems.size(); ++i) {
			ItemStack currentStack = this.craftSlots.getItem(i);
			ItemStack remainderStack = remainingItems.get(i);

			if (!currentStack.isEmpty()) {
				this.craftSlots.removeItem(i, 1);
				currentStack = this.craftSlots.getItem(i);
			}

			if (!remainderStack.isEmpty()) {
				if (currentStack.isEmpty()) {
					this.craftSlots.setItem(i, remainderStack);
				}
				else if (ItemStack.isSame(currentStack, remainderStack) && ItemStack.tagMatches(currentStack, remainderStack)) {
					remainderStack.grow(currentStack.getCount());
					this.craftSlots.setItem(i, remainderStack);
				}
				else if (!this.player.inventory.add(remainderStack)) {
					this.player.drop(remainderStack, false);
				}
			}
		}

		return stack;
	}
}
