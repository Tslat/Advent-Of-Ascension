package net.tslat.aoa3.capabilities.storages;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStack;

import javax.annotation.Nullable;

public class AdventMiscStackStorage implements Capability.IStorage<CapabilityBaseMiscStack> {
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<CapabilityBaseMiscStack> capability, CapabilityBaseMiscStack instance, EnumFacing side) {
		return null;
	}

	@Override
	public void readNBT(Capability<CapabilityBaseMiscStack> capability, CapabilityBaseMiscStack instance, EnumFacing side, NBTBase nbt) {}
}
