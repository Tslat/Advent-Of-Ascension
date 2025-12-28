package net.tslat.aoa3.library.object.interfaces;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public interface PartialNbtSerializable extends INBTSerializable<CompoundTag> {
    @Override
    default void deserializeNBT(HolderLookup.Provider registryLookup, CompoundTag baseTag) {
        load(baseTag, registryLookup, false);
    }

    @UnknownNullability
    @Override
    default CompoundTag serializeNBT(HolderLookup.Provider registryLookup) {
        final CompoundTag nbt = new CompoundTag();

        save(nbt, registryLookup, false);

        return nbt;
    }

    void load(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean isPartial);
    void save(CompoundTag nbt, HolderLookup.Provider holderLookup, boolean isPartial);
}
