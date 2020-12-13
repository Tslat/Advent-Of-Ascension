package net.tslat.aoa3.capabilities.persistentstack;

import net.minecraft.nbt.FloatNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PersistentStackStorage implements Capability.IStorage<PersistentStackCapabilityHandles> {
	private final Lazy<Capability<PersistentStackCapabilityHandles>> cap;

	public PersistentStackStorage(@Nonnull Lazy<Capability<PersistentStackCapabilityHandles>> capability) {
		this.cap = capability;
	}

	@Nullable
	@Override
	public INBT writeNBT(Capability<PersistentStackCapabilityHandles> capability, PersistentStackCapabilityHandles instance, Direction side) {
		return this.cap.get() == capability ? instance.serializeNBT() : null;
	}

	@Override
	public void readNBT(Capability<PersistentStackCapabilityHandles> capability, PersistentStackCapabilityHandles instance, Direction side, INBT nbt) {
		if (this.cap.get() == capability)
			instance.deserializeNBT((FloatNBT)nbt);
	}
}
