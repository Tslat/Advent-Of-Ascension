package net.tslat.aoa3.content.capability.adventplayer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Lazy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdventPlayerStorage implements Capability.IStorage<AdventPlayerCapabilityHandles> {
	private final Lazy<Capability<AdventPlayerCapabilityHandles>> cap;

	public AdventPlayerStorage(@Nonnull Lazy<Capability<AdventPlayerCapabilityHandles>> capability) {
		this.cap = capability;
	}

	@Nullable
	@Override
	public INBT writeNBT(Capability<AdventPlayerCapabilityHandles> capability, AdventPlayerCapabilityHandles instance, Direction side) {
		return this.cap.get() == capability ? instance.serializeNBT() : null;
	}

	@Override
	public void readNBT(Capability<AdventPlayerCapabilityHandles> capability, AdventPlayerCapabilityHandles instance, Direction side, INBT nbt) {
		if (this.cap.get() == capability)
			instance.deserializeNBT((CompoundNBT)nbt);
	}
}
