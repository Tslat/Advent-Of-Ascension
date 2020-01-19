package net.tslat.aoa3.capabilities.providers;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.tslat.aoa3.capabilities.interfaces.CapabilityBaseMiscStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AdventMiscStackProvider implements ICapabilityProvider {
	@CapabilityInject(CapabilityBaseMiscStack.class)
	public static Capability<CapabilityBaseMiscStack> MISC_STACK = null;
	private CapabilityBaseMiscStack instance = MISC_STACK.getDefaultInstance();

	public AdventMiscStackProvider() {}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == MISC_STACK;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == MISC_STACK ? MISC_STACK.<T>cast(instance) : null;
	}
}
