package net.tslat.aoa3.common.menu.slot;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.EventHooks;

public abstract class OutputSlot extends Slot {
    protected final Player player;
    protected int removedCount;

    public OutputSlot(Container inventoryContainer, Player player, int slotIndex, int x, int y) {
        super(inventoryContainer, slotIndex, x, y);

        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Override
    public boolean mayPickup(Player player) {
        return hasItem();
    }

    @Override
    public ItemStack remove(int amount) {
        onRemove(amount);

        return super.remove(amount);
    }

    public void onRemove(int amount) {
        if (hasItem())
            this.removedCount += Math.min(amount, getItem().getCount());
    }

    @Override
    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removedCount += amount;

        checkTakeAchievements(stack);
    }

    @Override
    protected void onSwapCraft(int amount) {
        this.removedCount += amount;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        onItemRemoved(player, stack);
        checkTakeAchievements(stack);
        super.onTake(player, stack);
    }

    public abstract void onItemRemoved(Player player, ItemStack stack);

    @Override
    protected void checkTakeAchievements(ItemStack stack) {
        if (this.removedCount > 0) {
            stack.onCraftedBy(this.player.level(), this.player, this.removedCount);
            EventHooks.firePlayerCraftingEvent(this.player, stack, this.container);
        }

        this.removedCount = 0;
    }
}
