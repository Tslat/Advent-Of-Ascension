package net.tslat.aoa3.object.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EmptyCapabilityStorage<T> implements Capability.IStorage<T> {
	@Nullable
	@Override
	public INBT writeNBT(Capability capability, Object instance, Direction side) {
		return null;
	}

	@Override
	public void readNBT(Capability capability, Object instance, Direction side, INBT nbt) {}
}
