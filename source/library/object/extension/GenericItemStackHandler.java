package net.tslat.aoa3.library.object.extension;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;

public class GenericItemStackHandler extends ItemStackHandler {
    public GenericItemStackHandler() {
        super();
    }

    public GenericItemStackHandler(int size) {
        super(size);
    }

    public GenericItemStackHandler(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

    public NonNullList<ItemStack> getAllStacks() {
        return this.stacks;
    }

    public void clear() {
        this.stacks.clear();
        allContentsChanged();
    }

    public void allContentsChanged() {
        for (int i = 0; i < this.stacks.size(); i++) {
            onContentsChanged(i);
        }
    }

    public void dropContentsInWorld(Level level, Vec3 pos) {
        for (ItemStack stack : this.stacks) {
            level.addFreshEntity(new ItemEntity(level, pos.x, pos.y, pos.z, stack));
        }

        clear();
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        if (nbt.contains("Contents", Tag.TAG_COMPOUND)) {
            this.stacks.clear();
            super.deserializeNBT(provider, nbt.getCompound("Contents"));
        }
    }
}
