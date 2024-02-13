package net.tslat.aoa3.common.menu.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class PredicatedSlot extends Slot {
    private final Predicate<ItemStack> predicate;

    public PredicatedSlot(Container inventory, int inventorySlotIndex, int x, int y, Predicate<ItemStack> predicate) {
        super(inventory, inventorySlotIndex, x, y);

        this.predicate = predicate;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return this.predicate.test(stack);
    }
}
