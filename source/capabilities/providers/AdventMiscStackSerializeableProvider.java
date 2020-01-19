package net.tslat.aoa3.capabilities.providers;

import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStackSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdventMiscStackSerializeableProvider implements ICapabilitySerializable<NBTTagFloat> {
	@CapabilityInject(CapabilityBaseMiscStackSerializable.class)
	public static Capability<CapabilityBaseMiscStackSerializable> MISC_STACK = null;
	private CapabilityBaseMiscStackSerializable instance = MISC_STACK.getDefaultInstance();

	public AdventMiscStackSerializeableProvider() {}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == MISC_STACK;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == MISC_STACK ? MISC_STACK.<T>cast(instance) : null;
	}

	@Override
	public NBTTagFloat serializeNBT() {
		return new NBTTagFloat(instance.getValue());
	}

	@Override
	public void deserializeNBT(NBTTagFloat nbt) {
		instance.setValue(nbt.getFloat());
	}
}
