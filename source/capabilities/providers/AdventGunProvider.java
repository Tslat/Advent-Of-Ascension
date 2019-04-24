package net.tslat.aoa3.capabilities.providers;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseGun;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdventGunProvider implements ICapabilityProvider {
	@CapabilityInject(CapabilityBaseGun.class)
	public static Capability<CapabilityBaseGun> ADVENT_GUN = null;
	private CapabilityBaseGun instance = ADVENT_GUN.getDefaultInstance();

	public AdventGunProvider() {}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == ADVENT_GUN;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == ADVENT_GUN ? ADVENT_GUN.<T>cast(instance) : null;
	}
}
