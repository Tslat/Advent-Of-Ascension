package net.tslat.aoa3.common.container;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public class CustomResultSlot<C extends Container, T extends Recipe<C>> extends Slot {
	private final RecipeType<T> recipeType;
	private final C craftSlots;
	private final Player player;
	private int removeCount;

	public CustomResultSlot(Player player, C craftSlots, Container container, RecipeType<T> recipeType, int slot, int xPosition, int yPosition) {
		super(container, slot, xPosition, yPosition);

		this.craftSlots = craftSlots;
		this.player = player;
		this.recipeType = recipeType;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack remove(int amount) {
		if (hasItem())
			this.removeCount += Math.min(amount, getItem().getCount());

		return super.remove(amount);
	}

	@Override
	protected void onQuickCraft(ItemStack stack, int amount) {
		this.removeCount += amount;

		checkTakeAchievements(stack);
	}

	@Override
	protected void onSwapCraft(int numItemsCrafted) {
		this.removeCount += numItemsCrafted;
	}

	@Override
	protected void checkTakeAchievements(ItemStack stack) {
		if (this.removeCount > 0) {
			stack.onCraftedBy(this.player.level, this.player, this.removeCount);
			ForgeEventFactory.firePlayerCraftingEvent(this.player, stack, this.craftSlots);
		}

		if (this.container instanceof RecipeHolder recipeHolder)
			recipeHolder.awardUsedRecipes(this.player);

		this.removeCount = 0;
	}



	@Override
	public void onTake(Player player, ItemStack stack) {
		checkTakeAchievements(stack);
		ForgeHooks.setCraftingPlayer(player);

		NonNullList<ItemStack> remainingItems = player.level.getRecipeManager().getRemainingItemsFor(recipeType, this.craftSlots, player.level);

		ForgeHooks.setCraftingPlayer(null);

		for(int i = 0; i < remainingItems.size(); ++i) {
			ItemStack slotStack = this.craftSlots.getItem(i);
			ItemStack remainingStack = remainingItems.get(i);

			if (!slotStack.isEmpty()) {
				this.craftSlots.removeItem(i, 1);

				slotStack = this.craftSlots.getItem(i);
			}

			if (!remainingStack.isEmpty()) {
				if (slotStack.isEmpty()) {
					this.craftSlots.setItem(i, remainingStack);
				}
				else if (ItemStack.isSame(slotStack, remainingStack) && ItemStack.tagMatches(slotStack, remainingStack)) {
					remainingStack.grow(slotStack.getCount());
					this.craftSlots.setItem(i, remainingStack);
				}
				else if (!this.player.getInventory().add(remainingStack)) {
					this.player.drop(remainingStack, false);
				}
			}
		}

	}
}
