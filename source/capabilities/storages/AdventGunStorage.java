package net.tslat.aoa3.capabilities.storages;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseGun;

import javax.annotation.Nullable;

public class AdventGunStorage implements Capability.IStorage<CapabilityBaseGun> {
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<CapabilityBaseGun> capability, CapabilityBaseGun instance, EnumFacing side) {
		return null;
	}

	@Override
	public void readNBT(Capability<CapabilityBaseGun> capability, CapabilityBaseGun instance, EnumFacing side, NBTBase nbt) {}
}
