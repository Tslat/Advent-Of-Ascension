package net.tslat.aoa3.capabilities.storages;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStackSerializable;

import javax.annotation.Nullable;

public class AdventMiscStackSerializableStorage implements Capability.IStorage<CapabilityBaseMiscStackSerializable> {
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<CapabilityBaseMiscStackSerializable> capability, CapabilityBaseMiscStackSerializable instance, EnumFacing side) {
		return new NBTTagFloat(instance.getValue());
	}

	@Override
	public void readNBT(Capability<CapabilityBaseMiscStackSerializable> capability, CapabilityBaseMiscStackSerializable instance, EnumFacing side, NBTBase nbt) {
		if (nbt instanceof NBTTagFloat)
			instance.setValue(((NBTTagFloat)nbt).getFloat());
	}
}
