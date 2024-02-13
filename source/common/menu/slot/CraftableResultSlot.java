package net.tslat.aoa3.common.menu.slot;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class CraftableResultSlot<C extends Container, R extends Recipe<C>> extends OutputSlot {
    private final C inventoryContainer;
    private final RecipeCraftingHolder resultContainer;
    @Nullable
    private final RecipeType<R> recipeType;

    public <T extends Container & RecipeCraftingHolder> CraftableResultSlot(Player player, C inventoryContainer, T resultContainer, @Nullable RecipeType<R> recipeType, int slotIndex, int x, int y) {
        super(resultContainer, player, slotIndex, x, y);

        this.recipeType = recipeType;
        this.inventoryContainer = inventoryContainer;
        this.resultContainer = resultContainer;
    }

    public RecipeCraftingHolder getResultContainer() {
        return this.resultContainer;
    }

    public Optional<R> getCurrentRecipe() {
        return Optional.ofNullable((RecipeHolder<R>)getResultContainer().getRecipeUsed()).map(RecipeHolder::value);
    }

    public C getInventoryContainer() {
        return this.inventoryContainer;
    }

    @Override
    protected void checkTakeAchievements(ItemStack stack) {
        if (this.removedCount > 0)
            awardForCrafting(stack, this.removedCount);

        getResultContainer().awardUsedRecipes(this.player, getContainerItems());
    }

    protected List<ItemStack> getContainerItems() {
        if (this.container instanceof CraftingContainer craftingContainer)
            return craftingContainer.getItems();

        if (this.container instanceof SimpleContainer simpleContainer)
            return simpleContainer.getItems();

        List<ItemStack> items = new ObjectArrayList<>(this.container.getContainerSize());

        for (int i = 0; i < this.container.getContainerSize(); i++) {
            items.add(this.container.getItem(i));
        }

        return items;
    }

    protected void awardForCrafting(ItemStack stack, int amount) {
        stack.onCraftedBy(this.player.level(), this.player, this.removedCount);
        EventHooks.firePlayerCraftingEvent(this.player, stack, this.inventoryContainer);
    }

    protected NonNullList<ItemStack> getRemainingItems(@Nullable RecipeType<R> recipeType, C craftingContainer, Player player) {
        return recipeType == null ? NonNullList.createWithCapacity(0) : player.level().getRecipeManager().getRemainingItemsFor(recipeType, craftingContainer, player.level());
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        if (getCurrentRecipe().isPresent())
            super.onTake(player, stack);
    }

    @Override
    public void onItemRemoved(Player player, ItemStack stack) {
        CommonHooks.setCraftingPlayer(player);

        NonNullList<ItemStack> remainderItems = getRemainingItems(this.recipeType, this.inventoryContainer, player);

        CommonHooks.setCraftingPlayer(null);

        for (int i = 0; i < remainderItems.size(); ++i) {
            ItemStack containerStack = this.inventoryContainer.getItem(i);
            ItemStack remainderStack = remainderItems.get(i);

            if (!containerStack.isEmpty()) {
                this.inventoryContainer.removeItem(i, 1);
                containerStack = this.inventoryContainer.getItem(i);
            }

            if (remainderStack.isEmpty())
                continue;

            if (containerStack.isEmpty()) {
                this.inventoryContainer.setItem(i, remainderStack);
            }
            else if (ItemStack.isSameItemSameTags(containerStack, remainderStack)) {
                remainderStack.grow(containerStack.getCount());
                this.inventoryContainer.setItem(i, remainderStack);
            }
            else if (!this.player.getInventory().add(remainderStack)) {
                this.player.drop(remainderStack, false);
            }
        }
    }

    @Override
    public boolean isFake() {
        return true;
    }
}
