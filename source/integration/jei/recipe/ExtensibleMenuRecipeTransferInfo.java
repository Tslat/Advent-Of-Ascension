package net.tslat.aoa3.integration.jei.recipe;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.tslat.aoa3.common.menu.generic.ExtensibleContainerMenu;

import java.util.List;
import java.util.Optional;

public class ExtensibleMenuRecipeTransferInfo<M extends ExtensibleContainerMenu<?>, R> implements IRecipeTransferInfo<M, R> {
    protected final Class<? extends M> menuClass;
    protected final MenuType<M> menuType;
    protected final RecipeType<R> recipeType;

    protected ExtensibleMenuRecipeTransferInfo(Class<? extends M> menuClass, MenuType<M> menuType, RecipeType<R> recipeType) {
        this.menuClass = menuClass;
        this.menuType = menuType;
        this.recipeType = recipeType;
    }

    @Override
    public Class<? extends M> getContainerClass() {
        return this.menuClass;
    }

    @Override
    public Optional<MenuType<M>> getMenuType() {
        return Optional.of(this.menuType);
    }

    @Override
    public RecipeType<R> getRecipeType() {
        return this.recipeType;
    }

    @Override
    public boolean canHandle(M container, R recipe) {
        return true;
    }

    @Override
    public List<Slot> getRecipeSlots(M container, R recipe) {
        final List<Slot> craftingSlots = new ObjectArrayList<>(container.inputSlotCount());

        for (int i = 0; i < container.inputSlotCount(); i++) {
            craftingSlots.add(container.getSlot(i));
        }

        return craftingSlots;
    }

    @Override
    public List<Slot> getInventorySlots(M container, R recipe) {
        final int inventoryStart = container.getOutputSlotIndex() + 1;
        final List<Slot> inventorySlots = new ObjectArrayList<>(container.slots.size() - inventoryStart);

        for (int i = inventoryStart; i < container.slots.size(); i++) {
            inventorySlots.add(container.getSlot(i));
        }

        return inventorySlots;
    }
}
