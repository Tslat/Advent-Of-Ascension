package net.tslat.aoa3.common.menu.container;

import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class ListenableResultContainer extends ResultContainer {
    private final Consumer<ItemStack> listener;

    public ListenableResultContainer(Consumer<ItemStack> listener) {
        this.listener = listener;
    }

    @Override
    public void setItem(int slotIndex, ItemStack stack) {
        super.setItem(slotIndex, stack);

        this.listener.accept(stack);
    }

    @Override
    public void clearContent() {
        super.clearContent();

        this.listener.accept(ItemStack.EMPTY);
    }

    @Override
    public ItemStack removeItem(int slotIndex, int count) {
        ItemStack stack = super.removeItem(slotIndex, count);

        this.listener.accept(ItemStack.EMPTY);

        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slotIndex) {
        ItemStack stack = super.removeItemNoUpdate(slotIndex);

        this.listener.accept(ItemStack.EMPTY);

        return stack;
    }
}