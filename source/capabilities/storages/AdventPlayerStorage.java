package net.tslat.aoa3.capabilities.storages;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBasePlayer;

import javax.annotation.Nullable;

public class AdventPlayerStorage implements Capability.IStorage<CapabilityBasePlayer> {
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<CapabilityBasePlayer> capability, CapabilityBasePlayer instance, EnumFacing side) {
		return instance.saveNBTData();
	}

	@Override
	public void readNBT(Capability<CapabilityBasePlayer> capability, CapabilityBasePlayer instance, EnumFacing side, NBTBase nbt) {
		if (nbt instanceof NBTTagCompound)
			instance.loadNBTData((NBTTagCompound)nbt);
	}
}
